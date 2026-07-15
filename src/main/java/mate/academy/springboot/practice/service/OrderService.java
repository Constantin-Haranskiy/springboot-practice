package mate.academy.springboot.practice.service;

import java.util.List;
import mate.academy.springboot.practice.dto.CreateOrderRequestDto;
import mate.academy.springboot.practice.dto.OrderDto;
import mate.academy.springboot.practice.dto.OrderItemDto;
import mate.academy.springboot.practice.dto.UpdateOrderStatusRequestDto;
import org.springframework.security.core.Authentication;

public interface OrderService {
    List<OrderDto> findAll(Authentication authentication);

    List<OrderItemDto> findById(Authentication authentication, Long orderId);

    OrderItemDto findItemById(Authentication authentication, Long orderId, Long itemId);

    OrderDto updateStatus(Long id, UpdateOrderStatusRequestDto updateRequestDto);

    OrderDto create(Authentication authentication, CreateOrderRequestDto createOrderRequestDto);
}
