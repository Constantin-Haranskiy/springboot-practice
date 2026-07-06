package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.springboot.practice.validation.Password;

@Data
public class LoginUserRequestDto {
    @Email
    @NotBlank
    private String email;
    @Password
    private String password;
}
