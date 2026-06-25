package mate.academy.springboot.practice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.springboot.practice.validation.FieldMatch;
import mate.academy.springboot.practice.validation.Password;

@Data
@FieldMatch(expected = "password", actual = "repeatPassword", message = "passwords do not match")
public class RegisterUserRequestDto {
    @Email
    private String email;
    @Password
    private String password;
    @Password
    private String repeatPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String shippingAddress;
}
