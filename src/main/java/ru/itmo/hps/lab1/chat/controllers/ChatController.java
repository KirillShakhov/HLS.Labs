package ru.itmo.hps.lab1.chat.controllers;

import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hps.lab1.chat.entity.Chat;
import ru.itmo.hps.lab1.chat.entity.Message;
import ru.itmo.hps.lab1.chat.service.ChatService;
import ru.itmo.hps.lab1.chat.service.MessageService;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ModelMapper modelMapper;
    private final ChatService chatService;

    @PostMapping("/create")
    private Mono<ChatDto> createChat(@RequestAttribute("username") String username, @RequestParam String title) {
        return chatService.addChat(title, username)
                .flatMap(chat ->
                        Mono.just(modelMapper.map(chat, ChatDto.class)));
    }
}
