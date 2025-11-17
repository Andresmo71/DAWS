package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class Controlador {

    
    @GetMapping("/greeting")
    public String greetingForm() {
        return "greeting";
    }

    
    @PostMapping("/greeting-post")
    public String greetingPost(@RequestParam("content") String content,
                               @RequestParam("id") String id,
                               Model model) {

        
        String encoded = URLEncoder.encode(content, StandardCharsets.UTF_8);

        model.addAttribute("id", id);
        model.addAttribute("content", content);
        model.addAttribute("encoded", encoded);

        return "result";
    }

    
    @GetMapping("/greeting-get")
    public String greetingGet(@RequestParam("content") String content,
                              @RequestParam("id") String id,
                              Model model) {

        model.addAttribute("id", id);
        model.addAttribute("content", content);
        model.addAttribute("encoded", URLEncoder.encode(content, StandardCharsets.UTF_8));

        return "result";
    }
}