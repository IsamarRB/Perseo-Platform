package com.Perseo_Platform.services;

import com.Perseo_Platform.models.CartItems;
import com.Perseo_Platform.repositories.ICartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsService {
    @Autowired
    private ICartItemsRepository cartItemsRepository;

    public List<CartItems> getAllCartItems() {return (List<CartItems>) cartItemsRepository.findAll();}

    public Optional<CartItems> getCartItemsById(int cartItemsId) {return cartItemsRepository.findById(cartItemsId);}

    public CartItems createCartItems(CartItems cartItems) {return cartItemsRepository.save(cartItems);}

    public CartItems updateCartItems(int cartItemsId, CartItems updatedCartItems) {
        return cartItemsRepository.findById(cartItemsId).map(cartItems -> {
            cartItems.setQuantity(updatedCartItems.getQuantity());
            cartItems.setUser(updatedCartItems.getUser());
            cartItems.setCourses(updatedCartItems.getCourses());
            return cartItemsRepository.save(cartItems);
        }).orElseThrow(() -> new RuntimeException("CartItems not found with id: " + cartItemsId));
    }

    public void deleteCartItems(int cartItemsId) {cartItemsRepository.deleteById(cartItemsId);}
}
