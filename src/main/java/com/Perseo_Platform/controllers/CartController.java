package com.Perseo_Platform.controllers;

import com.Perseo_Platform.models.Cart;
import com.Perseo_Platform.models.Course;
import com.Perseo_Platform.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")

public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping ("")
    public ResponseEntity<List<Cart>> getAllCarts() {List<Cart> carts = (List<Cart>) cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") int cartId) {
        Optional<Cart> cart = cartService.getCartById(cartId);
        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping ("")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {Cart createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") int cartId, @RequestBody Cart cart) {
        try {
            Cart updatedCart = cartService.updateCart(cartId, cart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") int cartId) {cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Cart> addCourseToCart(@PathVariable("id") int cartId, @RequestBody Course course) {
        try {
            Cart updatedCart = cartService.addCourseToCart(cartId, course);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/courses")
    public ResponseEntity<Cart> removeCourseFromCart(@PathVariable("id") int cartId, @RequestBody Course course) {
        try {
            Cart updatedCart = cartService.removeCourseFromCart(cartId, course);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
