package com.example.odev.service;

import com.example.odev.Model.Cart;
import com.example.odev.Model.CartItem;
import com.example.odev.Model.Product;
import com.example.odev.repo.CartItemRepository;
import com.example.odev.repo.CartRepository;
import com.example.odev.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        if (cart == null) {
            cart = new Cart();
            cartRepository.save(cart);  // İlk olarak cart nesnesini kaydediyoruz
        }

        // items listesinin null olup olmadığını kontrol et ve başlat
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            for (int i = 0; i < quantity; i++) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cart.getItems().add(cartItem);
                cartItemRepository.save(cartItem);  // Her CartItem nesnesini kaydediyoruz
            }
        }

        return cartRepository.save(cart);
    }

    public List<CartItem> getCartItems(Long cartId) {
        Cart cart = getCart(cartId);
        return cart != null ? cart.getItems() : null;
    }

    public void removeCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }
}
