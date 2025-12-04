package com.coltwarren.ChatBox_1.controller;

import com.coltwarren.ChatBox_1.domain.Message;
import com.coltwarren.ChatBox_1.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getLatestMessages();
    }

    @PostMapping
    public Message postMessage(@RequestBody NewMessageRequest request) {
        return messageService.saveMessage(request.getUsername(), request.getContent());
    }

    public static class NewMessageRequest {
        private String username;
        private String content;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
    }
}