package com.coltwarren.ChatBox_1.service;

import com.coltwarren.ChatBox_1.domain.ChatRoom;
import com.coltwarren.ChatBox_1.domain.Message;
import com.coltwarren.ChatBox_1.repository.ChatRoomRepository;
import com.coltwarren.ChatBox_1.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepo;
    private final ChatRoomRepository chatRoomRepo;

    public MessageService(MessageRepository messageRepo,
                          ChatRoomRepository chatRoomRepo) {
        this.messageRepo = messageRepo;
        this.chatRoomRepo = chatRoomRepo;
    }

    public List<Message> getLatestMessages(Long roomId) {
        List<Message> messages;

        if (roomId == null) {
            // fallback: no room filter
            messages = messageRepo.findTop50ByOrderByCreatedAtDesc();
        } else {
            messages = messageRepo.findTop50ByRoomIdOrderByCreatedAtDesc(roomId);
        }

        Collections.reverse(messages); // oldest → newest
        return messages;
    }

    public Message saveMessage(Long roomId, String username, String content) {
        // default to "General" if no room specified
        ChatRoom room;
        if (roomId != null) {
            room = chatRoomRepo.findById(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomId));
        } else {
            room = chatRoomRepo.findByName("General")
                    .orElseThrow(() -> new IllegalStateException("Default room 'General' not found"));
        }

        Message message = new Message();
        message.setUsername(username);
        message.setContent(content);
        message.setRoom(room);

        return messageRepo.save(message);
    }
}