package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.LoginUserRequestDto;
import mate.academy.springboot.practice.dto.LoginUserResponseDto;

public interface AuthenticationService {
    LoginUserResponseDto authenticate(LoginUserRequestDto loginUserRequestDto);
}
