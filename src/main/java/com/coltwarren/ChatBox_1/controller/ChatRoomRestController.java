package com.coltwarren.ChatBox_1.controller;

import com.coltwarren.ChatBox_1.domain.ChatRoom;
import com.coltwarren.ChatBox_1.repository.ChatRoomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomRestController {

    private final ChatRoomRepository chatRoomRepo;

    public ChatRoomRestController(ChatRoomRepository chatRoomRepo) {
        this.chatRoomRepo = chatRoomRepo;
    }

    @GetMapping
    public List<ChatRoom> listRooms() {
        return chatRoomRepo.findAll(Sort.by("name"));
    }
}