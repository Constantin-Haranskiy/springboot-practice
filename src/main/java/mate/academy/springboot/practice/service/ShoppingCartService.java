package mate.academy.springboot.practice.service;

import mate.academy.springboot.practice.dto.AddItemToCartRequestDto;
import mate.academy.springboot.practice.dto.CartItemDto;
import mate.academy.springboot.practice.dto.ShoppingCartDto;
import mate.academy.springboot.practice.dto.UpdateItemQuantityRequestDto;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    CartItemDto add(Authentication authentication, AddItemToCartRequestDto item);

    ShoppingCartDto findByUser(Authentication authentication);

    CartItemDto updateQuantity(Authentication authentication, Long cartItemId,
                               UpdateItemQuantityRequestDto quantityRequest);

    void delete(Authentication authentication, Long id);
}
