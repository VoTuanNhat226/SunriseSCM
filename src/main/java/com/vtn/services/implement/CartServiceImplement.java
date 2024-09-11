package com.vtn.services.implement;

import com.vtn.dto.cart.CartDetailsResponse;
import com.vtn.dto.product.ProductRequestAddToCart;
import com.vtn.pojo.Cart;
import com.vtn.pojo.CartDetails;
import com.vtn.pojo.Product;
import com.vtn.pojo.User;
import com.vtn.repository.CartDetailsRepository;
import com.vtn.repository.CartRepository;
import com.vtn.services.CartService;
import com.vtn.services.ProductService;
import com.vtn.util.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImplement implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Map<Long, CartDetailsResponse> getCartResponse(@NotNull Cart cart) {
        return Optional.ofNullable(cart.getCartDetailsSet()).orElseGet(Set::of).parallelStream()
                .collect(Collectors.toMap(cd -> cd.getProduct().getId(), this::getCartDetailsResponse));
    }

    @Override
    public CartDetailsResponse getCartDetailsResponse(@NotNull CartDetails cartDetails) {
        return CartDetailsResponse.builder()
                .quantity(cartDetails.getQuantity())
                .unitPrice(cartDetails.getUnitPrice())
                .product(this.productService.getProductResponseForList(cartDetails.getProduct()))
                .build();
    }

    @Override
    public Cart findCartByUser(@NotNull User user) {
        return Optional.ofNullable(user.getCart()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            this.cartRepository.save(newCart);
            return newCart;
        });
    }

    @Override
    public void addProductToCart(@NotNull Cart cart, @NotNull ProductRequestAddToCart productRequestAddToCart) {
        Product product = this.productService.findById(productRequestAddToCart.getProductId());
        CartDetails existingCartDetails = Optional.ofNullable(cart.getCartDetailsSet()).orElseGet(Set::of).parallelStream()
                .filter(cd -> cd.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingCartDetails != null) {
            existingCartDetails.setQuantity(existingCartDetails.getQuantity() + productRequestAddToCart.getQuantity());
            this.cartDetailsRepository.update(existingCartDetails);
        } else {
            CartDetails cartDetails = CartDetails.builder()
                    .cart(cart)
                    .product(product)
                    .unitPrice(product.getPrice())
                    .quantity(productRequestAddToCart.getQuantity())
                    .build();
            this.cartDetailsRepository.save(cartDetails);
        }
    }

    @Override
    public void updateProductInCart(@NotNull Cart cart, Long productId, @NotNull Map<String, String> params) {
        CartDetails cartDetails = Optional.ofNullable(cart.getCartDetailsSet()).orElseGet(Set::of).parallelStream()
                .filter(cd -> cd.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng."));

        params.forEach((key, value) -> {
            try {
                Field cartDetailsField = CartDetails.class.getDeclaredField(key);
                cartDetailsField.setAccessible(true);
                Object convertedValue = Utils.convertValue(cartDetailsField.getType(), value);
                cartDetailsField.set(cartDetails, convertedValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, e);
            }
        });

        this.cartDetailsRepository.update(cartDetails);
    }

    @Override
    public void deleteProductFromCart(@NotNull Cart cart, Long productId) {
        CartDetails cartDetails = Optional.ofNullable(cart.getCartDetailsSet()).orElseGet(Set::of).parallelStream()
                .filter(cd -> cd.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng."));

        this.cartDetailsRepository.delete(cartDetails.getId());
    }

    @Override
    public void clearCart(@NotNull Cart cart) {
        cart.getCartDetailsSet().forEach(cd -> this.cartDetailsRepository.delete(cd.getId()));
    }
}
