package mate.academy.springboot.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.RegisterUserRequestDto;
import mate.academy.springboot.practice.dto.UserDto;
import mate.academy.springboot.practice.exception.RegistrationException;
import mate.academy.springboot.practice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management", description = "Endpoints for authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register User", description = "Register User")
    public UserDto register(@RequestBody @Valid RegisterUserRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
