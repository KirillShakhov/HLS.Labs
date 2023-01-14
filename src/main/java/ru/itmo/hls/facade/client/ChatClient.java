package ru.itmo.hls.facade.client;

import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;

@ReactiveFeignClient(name = "chat-service")
public interface ChatClient {
    @PostMapping("/api/v1/chats/create")
    Mono<ChatDto> createChat(@RequestHeader String username, @RequestParam String title);

    @GetMapping("/api/v1/chats/get")
    Flux<ChatDto> getChats(@RequestHeader String username);

    @GetMapping("/api/v1/chats/get/{id}")
    Mono<ChatDto> getChatsById(@RequestHeader String username, @PathVariable Long id);

    @PostMapping("/api/v1/chats/send/{id}")
    Mono<ChatDto> sendById(@RequestHeader String username, @PathVariable Long id, @RequestParam String text);
}
