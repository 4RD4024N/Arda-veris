package com.example.odev.Controller;

import com.example.odev.Model.Person;
import com.example.odev.repo.ExampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DatabaseController {
    //Database bağlantısını sağlayan controller
    @Autowired
    private ExampleRepository personRepository;



    @GetMapping("/anasayfa")
    public String anasayfa() {
        return "anasayfa";
    }

    @GetMapping("/iletisim")
    public String iletisim() {
        return "iletisim";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/uye")
    public String uye() {
        return "uye";
    }

}
