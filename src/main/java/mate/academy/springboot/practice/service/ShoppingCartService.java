package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.AddItemToCartRequestDto;
import mate.academy.springboot.practice.dto.ShoppingCartDto;
import mate.academy.springboot.practice.dto.UpdateItemQuantityRequestDto;
import mate.academy.springboot.practice.model.User;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartDto add(Authentication authentication, AddItemToCartRequestDto item);

    ShoppingCartDto findByUser(Authentication authentication);

    ShoppingCartDto updateQuantity(Authentication authentication, Long cartItemId,
                               UpdateItemQuantityRequestDto quantityRequest);

    void delete(Authentication authentication, Long id);

    void createUserShoppingCart(User user);
}
