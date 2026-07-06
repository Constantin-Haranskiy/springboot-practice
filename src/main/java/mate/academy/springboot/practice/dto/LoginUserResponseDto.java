package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserResponseDto {
    @NotBlank
    private String token;

    public LoginUserResponseDto(String token) {
        this.token = token;
    }
}
