package com.coltwarren.ChatBox_1.service;

import com.coltwarren.ChatBox_1.domain.Message;
import com.coltwarren.ChatBox_1.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepo;

    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getLatestMessages() {
        List<Message> messages = messageRepo.findTop50ByOrderByCreatedAtDesc();
        // reverse so they show oldest → newest
        Collections.reverse(messages);
        return messages;
    }

    public Message saveMessage(String username, String content) {
        Message message = new Message();
        message.setUsername(username);
        message.setContent(content);
        return messageRepo.save(message);
    }
}
