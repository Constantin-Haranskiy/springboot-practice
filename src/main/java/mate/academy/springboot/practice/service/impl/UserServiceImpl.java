package mate.academy.springboot.practice.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.RegisterUserRequestDto;
import mate.academy.springboot.practice.dto.UserDto;
import mate.academy.springboot.practice.exception.RegistrationException;
import mate.academy.springboot.practice.mapper.UserMapper;
import mate.academy.springboot.practice.model.Role;
import mate.academy.springboot.practice.model.User;
import mate.academy.springboot.practice.repository.RoleRepository;
import mate.academy.springboot.practice.repository.UserRepository;
import mate.academy.springboot.practice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(registerUserRequestDto.getEmail())) {
            throw new RegistrationException("User with email: "
                    + registerUserRequestDto.getEmail() + "already exists");
        }
        Role role = roleRepository.getByName(Role.RoleName.USER).orElseThrow(
                () -> new RegistrationException(Role.RoleName.USER + " role not found")
        );
        User user = userMapper.toModel(registerUserRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(role));
        return userMapper.toDto(userRepository.save(user));
    }
}
