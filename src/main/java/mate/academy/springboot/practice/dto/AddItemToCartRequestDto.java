package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddItemToCartRequestDto {
    @Positive
    @NotNull
    private Long bookId;
    @Positive
    @NotNull
    private Integer quantity;
}
