package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.facade.client.AuthClient;
import ru.itmo.hls.facade.client.ChatClient;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @PostMapping("/create")
    Mono<ChatDto> createChat(@RequestParam String title, @RequestParam String user){
        return chatClient.createChat(title, user);
    }
}
