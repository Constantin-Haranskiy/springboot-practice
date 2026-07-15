package mate.academy.springboot.practice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.CreateOrderRequestDto;
import mate.academy.springboot.practice.dto.OrderDto;
import mate.academy.springboot.practice.dto.OrderItemDto;
import mate.academy.springboot.practice.dto.UpdateOrderStatusRequestDto;
import mate.academy.springboot.practice.exception.AuthenticationException;
import mate.academy.springboot.practice.exception.EntityNotFoundException;
import mate.academy.springboot.practice.exception.OrderProcessingException;
import mate.academy.springboot.practice.helper.UserHelper;
import mate.academy.springboot.practice.mapper.OrderItemMapper;
import mate.academy.springboot.practice.mapper.OrderMapper;
import mate.academy.springboot.practice.model.CartItem;
import mate.academy.springboot.practice.model.Order;
import mate.academy.springboot.practice.model.OrderItem;
import mate.academy.springboot.practice.model.OrderStatus;
import mate.academy.springboot.practice.model.ShoppingCart;
import mate.academy.springboot.practice.model.User;
import mate.academy.springboot.practice.repository.CartItemRepository;
import mate.academy.springboot.practice.repository.OrderItemRepository;
import mate.academy.springboot.practice.repository.OrderRepository;
import mate.academy.springboot.practice.repository.ShoppingCartRepository;
import mate.academy.springboot.practice.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final UserHelper userHelper;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<OrderDto> findAll(Authentication authentication) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();
        return orderRepository.findByUserId(userId)
                .map(orders -> orders.stream().map(orderMapper::toDto).toList())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found order for user with id: " + userId));
    }

    @Override
    public List<OrderItemDto> findById(Authentication authentication, Long orderId) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Not found order with id: " + orderId)
        );
        if (!order.getUser().getId().equals(userId)) {
            throw new AuthenticationException("Access denied");
        }

        return orderMapper.toDto(order).getOrderItems();
    }

    @Override
    public OrderItemDto findItemById(Authentication authentication, Long orderId, Long itemId) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Not found order with id: " + orderId)
        );
        if (!order.getUser().getId().equals(userId)) {
            throw new AuthenticationException("Access denied");
        }

        return order.getOrderItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .map(orderItemMapper::toDto)
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException("Not found item with id: " + itemId));
    }

    @Override
    public OrderDto updateStatus(Long id, UpdateOrderStatusRequestDto updateRequestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found order with id: " + id)
        );
        order.setStatus(updateRequestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto create(Authentication authentication,
                           CreateOrderRequestDto createOrderRequestDto) {
        User user = userHelper.getUserOrThrow(authentication);
        ShoppingCart cart = shoppingCartRepository.findById(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart not found "
                        + "by user with id: " + user.getId())
        );

        if (cart.getCartItems().isEmpty()) {
            throw new OrderProcessingException("Shopping cart is empty");
        }
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setShippingAddress(createOrderRequestDto.getShippingAddress());
        order.setUser(user);

        BigDecimal totalPrice = BigDecimal.ZERO;

        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            orderItem.setPrice(cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItems.add(orderItem);

            totalPrice = totalPrice.add(orderItem.getPrice());
        }

        order.setTotal(totalPrice);

        Order savedOrder = orderRepository.save(order);
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
        savedOrder.setOrderItems(new HashSet<>(savedOrderItems));

        cartItemRepository.deleteAll(cart.getCartItems());
        return orderMapper.toDto(savedOrder);
    }
}
