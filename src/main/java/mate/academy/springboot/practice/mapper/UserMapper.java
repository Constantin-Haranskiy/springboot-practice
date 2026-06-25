package mate.academy.springboot.practice.mapper;

import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.RegisterUserRequestDto;
import mate.academy.springboot.practice.dto.UserDto;
import mate.academy.springboot.practice.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User book);

    User toModel(RegisterUserRequestDto requestDto);
}
