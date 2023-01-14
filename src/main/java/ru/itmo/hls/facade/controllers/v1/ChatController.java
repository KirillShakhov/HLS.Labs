package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hls.facade.client.ChatClient;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @PostMapping("/create")
    Mono<ChatDto> createChat(@RequestAttribute("username") String username, @RequestParam String title){
        return chatClient.createChat(username, title);
    }

    @GetMapping("/get")
    Flux<ChatDto> getChats(@RequestAttribute("username") String username){
        return chatClient.getChats(username);
    }

    @GetMapping("/get/{id}")
    Mono<ChatDto> getChatsById(@RequestAttribute("username") String username, @PathVariable Long id){
        return chatClient.getChatsById(username, id);
    }

    @PostMapping("/send/{id}")
    Mono<ChatDto> sendById(@RequestAttribute("username") String username, @PathVariable Long id, @RequestParam String text){
        return chatClient.sendById(username, id, text);
    }
}
