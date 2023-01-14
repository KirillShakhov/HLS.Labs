package ru.itmo.hps.lab1.chat.controllers;

import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hps.lab1.chat.service.ChatService;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ModelMapper modelMapper;
    private final ChatService chatService;

    @PostMapping("/create")
    private Mono<ChatDto> createChat(@RequestHeader("username") String username, @RequestParam String title) {
        System.out.println(username);
        return chatService.addChat(title, username)
                .flatMap(chat ->
                        Mono.just(modelMapper.map(chat, ChatDto.class)));
    }

    @GetMapping("/get")
    private Flux<ChatDto> getChats(@RequestHeader("username") String username) {
        return chatService.getChat(username).flatMap(chat -> Mono.just(modelMapper.map(chat, ChatDto.class)));
    }

    @GetMapping("/get/{id}")
    private Mono<ChatDto> getChatsById(@RequestHeader("username") String username, @PathVariable Long id) {
        return chatService.getChatById(username, id).flatMap(chat -> Mono.just(modelMapper.map(chat, ChatDto.class)));
    }

    @PostMapping("/send/{id}")
    private Mono<ChatDto> sendById(@RequestHeader("username") String username, @PathVariable Long id, @RequestParam String text) {
        return chatService.sendById(username, id, text).flatMap(chat -> Mono.just(modelMapper.map(chat, ChatDto.class)));
    }
}
