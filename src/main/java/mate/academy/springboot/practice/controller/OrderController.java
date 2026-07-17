package mate.academy.springboot.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.CreateOrderRequestDto;
import mate.academy.springboot.practice.dto.OrderDto;
import mate.academy.springboot.practice.dto.OrderItemDto;
import mate.academy.springboot.practice.dto.UpdateOrderStatusRequestDto;
import mate.academy.springboot.practice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get user orders", description = "Get user orders")
    public List<OrderDto> getUserOrders(Authentication authentication) {
        return orderService.findAllByUser(authentication);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create order", description = "Create order")
    public OrderDto createOrder(Authentication authentication,
                                  @RequestBody @Valid CreateOrderRequestDto createOrderRequestDto) {
        return orderService.create(authentication, createOrderRequestDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public OrderDto updateOrderStatus(@PathVariable Long id,
                                      @RequestBody @Valid UpdateOrderStatusRequestDto requestDto) {
        return orderService.updateStatus(id, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get items of user order", description = "Get items of user order")
    public List<OrderItemDto> getOrderItems(Authentication authentication,
                                            @PathVariable Long orderId) {
        return orderService.findOrderItemsByOrder(authentication, orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get items of user order", description = "Get items of user order")
    public OrderItemDto getOrderItems(Authentication authentication,
                                            @PathVariable Long orderId,
                                            @PathVariable Long itemId) {
        return orderService.findOrderItemByIdAndOrder(authentication, orderId, itemId);
    }
}
