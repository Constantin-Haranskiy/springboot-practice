package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateItemQuantityRequestDto {
    @Positive
    private int quantity;
}
