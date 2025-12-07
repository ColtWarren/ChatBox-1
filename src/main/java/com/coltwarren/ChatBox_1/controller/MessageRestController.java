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
    public List<Message> getMessages(@RequestParam(required = false) Long roomId) {
        return messageService.getLatestMessages(roomId);
    }

    @PostMapping
    public Message postMessage(@RequestBody NewMessageRequest request) {
        return messageService.saveMessage(
                request.getRoomId(),
                request.getUsername(),
                request.getContent()
        );
    }

    public static class NewMessageRequest {
        private String username;
        private String content;
        private Long roomId;

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

        public Long getRoomId() {
            return roomId;
        }
        public void setRoomId(Long roomId) {
            this.roomId = roomId;
        }
    }
}