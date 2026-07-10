package mate.academy.springboot.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.AddItemToCartRequestDto;
import mate.academy.springboot.practice.dto.CartItemDto;
import mate.academy.springboot.practice.dto.ShoppingCartDto;
import mate.academy.springboot.practice.dto.UpdateItemQuantityRequestDto;
import mate.academy.springboot.practice.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final ShoppingCartService cartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get user shopping cart", description = "Get user shopping cart")
    public ShoppingCartDto getUserCart(Authentication authentication) {
        return cartService.findByUser(authentication);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add item to cart", description = "Add item to shopping cart")
    public CartItemDto addItemToCart(Authentication authentication,
                                     @RequestBody @Valid AddItemToCartRequestDto cartItemDto) {
        return cartService.add(authentication, cartItemDto);
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update item quantity",
            description = "Update item quantity in shopping cart")
    public CartItemDto updateItemQuantity(Authentication authentication,
                                          @PathVariable Long id,
                                          @RequestBody @Valid
                                          UpdateItemQuantityRequestDto itemQuantityRequestDto) {
        return cartService.updateQuantity(authentication, id, itemQuantityRequestDto);
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete item", description = "Delete item from shopping cart")
    public void deleteItem(Authentication authentication, @PathVariable Long id) {
        cartService.delete(authentication, id);
    }

}
