//package com.coltwarren.ChatBox_1.controller;
//
//import com.coltwarren.ChatBox_1.domain.Message;
//import com.coltwarren.ChatBox_1.service.MessageService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ChatApiController {
//
//    private final MessageService messageService;
//
//    public ChatApiController(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    @PostMapping("/chat")
//    public Message handleChatPost(@RequestBody NewMessageRequest request) {
//        return messageService.saveMessage(
//                request.getUsername(),
//                request.getContent(),
//                request.getRoomId()
//        );
//    }
//
//    public static class NewMessageRequest {
//        private String username;
//        private String content;
//        private Long roomId;
//
//        public String getUsername() {
//            return username;
//        }
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getContent() {
//            return content;
//        }
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public Long getRoomId() {
//            return roomId;
//        }
//        public void setRoomId(Long roomId) {
//            this.roomId = roomId;
//        }
//    }
//}