package ru.itmo.hls.facade.client;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;

import javax.validation.constraints.NotNull;

@ReactiveFeignClient(name = "chat-service")
public interface ChatClient {
    @PostMapping("/api/v1/chats/create")
    Mono<ChatDto> createChat(@RequestParam String title, @RequestParam String user);
}
