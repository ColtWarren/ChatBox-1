package com.coltwarren.ChatBox_1;

import com.coltwarren.ChatBox_1.domain.ChatRoom;
import com.coltwarren.ChatBox_1.repository.ChatRoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatBox1Application {

    public static void main(String[] args) {
        SpringApplication.run(ChatBox1Application.class, args);
    }

    @Bean
    CommandLineRunner initRooms(ChatRoomRepository chatRoomRepo) {
        return args -> {
            if (chatRoomRepo.count() == 0) {
                ChatRoom general = new ChatRoom();
                general.setName("General");
                chatRoomRepo.save(general);

                ChatRoom java = new ChatRoom();
                java.setName("Java");
                chatRoomRepo.save(java);

                ChatRoom random = new ChatRoom();
                random.setName("Random");
                chatRoomRepo.save(random);
            }
        };
    }
}
