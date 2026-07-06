package mate.academy.springboot.practice.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.LoginUserRequestDto;
import mate.academy.springboot.practice.dto.LoginUserResponseDto;
import mate.academy.springboot.practice.service.AuthenticationService;
import mate.academy.springboot.practice.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginUserResponseDto authenticate(LoginUserRequestDto loginUserRequestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequestDto.getEmail(),
                        loginUserRequestDto.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginUserResponseDto(token);
    }
}
