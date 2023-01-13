package ru.itmo.hps.lab1.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Chat;
import ru.itmo.hps.lab1.chat.repositories.ChatRepository;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Mono<Chat> getChatById(Long id){
        return chatRepository.findById(id);
    }

    public Mono<Chat> addChat(String title, String adminUser){
        return chatRepository.save(Chat.builder()
                        .title(title)
                        .adminUser(adminUser)
                        .users(new HashSet<>())
                .build());
    }
}
