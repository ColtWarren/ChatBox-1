package com.coltwarren.ChatBox_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    // If you go to http://localhost:8080/ → redirect to /chat
    @GetMapping("/")
    public String redirectToChat() {
        System.out.println("Hello");
        return "redirect:/chat";
    }

    // If you go to http://localhost:8080/chat → render chat.html
    @GetMapping("/chat")
    public String chatPage() {
        System.out.println("Hello Me");
        return "chat"; // looks for src/main/resources/templates/chat.html
    }
}