package mate.academy.springboot.practice.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.practice.dto.AddItemToCartRequestDto;
import mate.academy.springboot.practice.dto.ShoppingCartDto;
import mate.academy.springboot.practice.dto.UpdateItemQuantityRequestDto;
import mate.academy.springboot.practice.exception.AuthenticationException;
import mate.academy.springboot.practice.exception.EntityNotFoundException;
import mate.academy.springboot.practice.exception.InsertionException;
import mate.academy.springboot.practice.helper.UserHelper;
import mate.academy.springboot.practice.mapper.CartItemMapper;
import mate.academy.springboot.practice.mapper.ShoppingCartMapper;
import mate.academy.springboot.practice.model.Book;
import mate.academy.springboot.practice.model.CartItem;
import mate.academy.springboot.practice.model.ShoppingCart;
import mate.academy.springboot.practice.model.User;
import mate.academy.springboot.practice.repository.BookRepository;
import mate.academy.springboot.practice.repository.CartItemRepository;
import mate.academy.springboot.practice.repository.ShoppingCartRepository;
import mate.academy.springboot.practice.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookRepository bookRepository;
    private final UserHelper userHelper;

    @Override
    public ShoppingCartDto add(Authentication authentication, AddItemToCartRequestDto item) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();

        ShoppingCart shoppingCart = getShoppingCartByUserIdOrThrow(userId);

        shoppingCart.getCartItems().forEach(cartItem -> {
            if (cartItem.getBook().getId().equals(item.getBookId())) {
                throw new InsertionException("Book with id: " + item.getBookId()
                    + " already exists in shopping cart.");
            }
        });

        Book book = bookRepository.findById(item.getBookId()).orElseThrow(
                () -> new EntityNotFoundException("Not found book with id: " + item.getBookId())
        );

        CartItem cartItem = cartItemMapper.toModel(item);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItemRepository.save(cartItem);
        shoppingCart.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto findByUser(Authentication authentication) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();

        return shoppingCartMapper.toDto(getShoppingCartByUserIdOrThrow(userId));
    }

    @Override
    public ShoppingCartDto updateQuantity(Authentication authentication, Long cartItemId,
                                      UpdateItemQuantityRequestDto quantityRequest) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Item not found "
                        + "by id: " + cartItemId)
        );

        if (!cartItem.getShoppingCart().getId().equals(userId)) {
            throw new AuthenticationException("Access to update this item is denied");
        }

        cartItemMapper.updateCartItem(quantityRequest, cartItem);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(getShoppingCartByUserIdOrThrow(userId));
    }

    @Override
    public void delete(Authentication authentication, Long cartItemId) {
        Long userId = userHelper.getUserOrThrow(authentication).getId();

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Item not found "
                        + "by id: " + cartItemId)
        );

        if (!cartItem.getShoppingCart().getId().equals(userId)) {
            throw new AuthenticationException("Access to update this item is denied");
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    public void createUserShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart getShoppingCartByUserIdOrThrow(Long userId) {
        return shoppingCartRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart not found "
                        + "by user with id: " + userId)
        );
    }
}
