package mate.academy.springboot.practice.mapper;

import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.OrderItemDto;
import mate.academy.springboot.practice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Named("orderItemToDto")
    OrderItemDto toDto(OrderItem orderItem);
}
