package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.springboot.practice.model.OrderStatus;

@Data
public class UpdateOrderStatusRequestDto {
    @NotNull
    private OrderStatus status;
}
