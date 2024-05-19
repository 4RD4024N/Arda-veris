package com.example.odev.Controller;

import com.example.odev.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/sepet")
    public String getCartItems(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        Long cartId = 1L; // veya kullanıcıya ait sepet ID'sini belirleyin
        model.addAttribute("cartItems", cartService.getCartItems(cartId));
        return "sepet";
    }

    @PostMapping("/sepet/ekle/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam("quantity") int quantity, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        Long cartId = 1L; // veya kullanıcıya ait sepet ID'sini belirleyin
        cartService.addProductToCart(cartId, productId, quantity);
        return "redirect:/urunler";
    }

    @PostMapping("/sepet/sil/{itemId}")
    public String removeFromCart(@PathVariable Long itemId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        cartService.removeCartItem(itemId);
        return "redirect:/sepet";
    }
}
