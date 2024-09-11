package com.vtn.controllers.api;

import com.vtn.dto.MessageResponse;
import com.vtn.dto.product.ProductRequestAddToCart;
import com.vtn.pojo.Cart;
import com.vtn.pojo.User;
import com.vtn.services.CartService;
import com.vtn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping(path = "/api/cart", produces = "application/json; charset=UTF-8")
public class APICartController {

    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getCart(Principal principal) {
        User user = this.userService.findByUsername(principal.getName());
        Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("không tìm thấy người dùng"));

        Cart cart = this.cartService.findCartByUser(user);

        return ResponseEntity.ok(this.cartService.getCartResponse(cart));
    }

    @PostMapping(path = "/product/add")
    public ResponseEntity<?> addProductToCart(Principal principal, @RequestBody ProductRequestAddToCart productRequestAddToCart) {
        User user = this.userService.findByUsername(principal.getName());
        Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("không tìm thấy người dùng"));

        Cart cart = this.cartService.findCartByUser(user);

        this.cartService.addProductToCart(cart, productRequestAddToCart);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/product/{productId}/update")
    public ResponseEntity<?> updateProductInCart(Principal principal, @PathVariable(value = "productId") Long productId,
                                                 @RequestBody Map<String, String> params) {
        User user = this.userService.findByUsername(principal.getName());
        Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("không tìm thấy người dùng"));

        Cart cart = this.cartService.findCartByUser(user);

        this.cartService.updateProductInCart(cart, productId, params);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/product/{productId}/remove")
    public ResponseEntity<?> deleteProductFromCart(Principal principal, @PathVariable(value = "productId") Long productId) {
        User user = this.userService.findByUsername(principal.getName());
        Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("không tìm thấy người dùng"));

        Cart cart = this.cartService.findCartByUser(user);

        this.cartService.deleteProductFromCart(cart, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/product/clear")
    public ResponseEntity<?> clearCart(Principal principal) {
        User user = this.userService.findByUsername(principal.getName());
        Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException("không tìm thấy người dùng"));

        this.cartService.clearCart(this.cartService.findCartByUser(user));

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(@NotNull HttpServletRequest req, EntityNotFoundException e) {
        LoggerFactory.getLogger(req.getRequestURI()).error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(new MessageResponse(e.getMessage())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(@NotNull HttpServletRequest req, AccessDeniedException e) {
        LoggerFactory.getLogger(req.getRequestURI()).error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(new MessageResponse(e.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(@NotNull HttpServletRequest req, Exception e) {
        LoggerFactory.getLogger(req.getRequestURI()).error(e.getMessage(), e);

        return ResponseEntity.badRequest().body(List.of(new MessageResponse(e.getMessage())));
    }
}
