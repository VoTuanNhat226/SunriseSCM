package com.vtn.services;

import com.vtn.dto.cart.CartDetailsResponse;
import com.vtn.dto.product.ProductRequestAddToCart;
import com.vtn.pojo.Cart;
import com.vtn.pojo.CartDetails;
import com.vtn.pojo.User;

import java.util.Map;

public interface CartService {

    Map<Long, CartDetailsResponse> getCartResponse(Cart cart);

    CartDetailsResponse getCartDetailsResponse(CartDetails cartDetails);

    Cart findCartByUser(User user);

    void addProductToCart(Cart cart, ProductRequestAddToCart productRequestAddToCart);

    void updateProductInCart(Cart cart, Long productId, Map<String, String> params);

    void deleteProductFromCart(Cart cart, Long productId);

    void clearCart(Cart cart);
}
