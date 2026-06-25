package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.RegisterUserRequestDto;
import mate.academy.springboot.practice.dto.UserDto;
import mate.academy.springboot.practice.exception.RegistrationException;

public interface UserService {
    UserDto register(RegisterUserRequestDto registerUserRequestDto) throws RegistrationException;
}
