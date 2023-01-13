package ru.itmo.hps.lab1.chat.controllers;

import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Chat;
import ru.itmo.hps.lab1.chat.entity.Message;
import ru.itmo.hps.lab1.chat.service.ChatService;
import ru.itmo.hps.lab1.chat.service.MessageService;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ModelMapper modelMapper;
    private final ChatService chatService;

    @GetMapping("/create")
    private Mono<Chat> createChat(@RequestParam String title, @RequestParam String user) {
        return chatService.addChat(title, user)
                .flatMap(departmentEntity ->
                        Mono.just(modelMapper.map(departmentEntity, Chat.class)));

    }
}
