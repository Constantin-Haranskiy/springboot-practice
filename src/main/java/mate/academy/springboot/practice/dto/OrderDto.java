package mate.academy.springboot.practice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import mate.academy.springboot.practice.model.OrderStatus;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private OrderStatus status;
}
