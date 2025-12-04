package com.coltwarren.ChatBox_1.repository;

import com.coltwarren.ChatBox_1.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // latest 50 messages (newest first)
    List<Message> findTop50ByOrderByCreatedAtDesc();
}

