package mate.academy.springboot.practice.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.RegisterUserRequestDto;
import mate.academy.springboot.practice.dto.UserDto;
import mate.academy.springboot.practice.exception.RegistrationException;
import mate.academy.springboot.practice.mapper.UserMapper;
import mate.academy.springboot.practice.model.User;
import mate.academy.springboot.practice.repository.UserRepository;
import mate.academy.springboot.practice.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(registerUserRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User already exists");
        }

        User user = userMapper.toModel(registerUserRequestDto);
        user.setPassword(user.getPassword());
        return userMapper.toDto(userRepository.save(user));
    }
}
