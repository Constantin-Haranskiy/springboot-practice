package mate.academy.springboot.practice.mapper;

import mate.academy.springboot.practice.config.MapperConfig;
import mate.academy.springboot.practice.dto.ShoppingCartDto;
import mate.academy.springboot.practice.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "cartItemToDto")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
