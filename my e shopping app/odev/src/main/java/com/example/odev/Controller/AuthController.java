package com.example.odev.Controller;

import com.example.odev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register"; // register.html adındaki Thymeleaf şablonunu döndürür
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
                               @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if (userService.findByUsername(username) != null) {
            model.addAttribute("error", "Kullanıcı adı zaten mevcut");
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Şifreler eşleşmiyor.");
            return "register";
        }

        try {
            userService.registerNewUser(username, email, firstName, lastName, birthDate, password);
        } catch (Exception e) {
            model.addAttribute("error", "Kullanıcı kaydı sırasında bir hata oluştu: " + e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // login.html adındaki Thymeleaf şablonunu döndürür
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (userService.authenticate(username, password)) {
            session.setAttribute("username", username); // Oturumda kullanıcı adını sakla
            return "redirect:/index"; // Girişten sonra ana sayfaya yönlendir
        } else {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre");
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Oturumu sonlandır
        return "redirect:/index"; // Çıkış yaptıktan sonra ana sayfaya yönlendir
    }
}
