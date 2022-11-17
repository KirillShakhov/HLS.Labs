package ru.itmo.hps.lab1.chat.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.payload.Message;
import ru.itmo.hps.lab1.chat.service.ChatService;
import ru.itmo.hps.lab1.chat.service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ChatService chatService;
    private final MessageService messageService;


//    @GetMapping("/get/{id}")
//    private Mono<Chat> getChatById(@PathVariable Long id) {
//        return chatService.getChatById(id);
//    }

    @GetMapping("/get/{id}")
    private Flux<Message> getChatById(@PathVariable Long id) {
        return messageService.getMessagesByChatId(id);
    }

    @PostMapping(value="/sendMessage")
    private Mono<Boolean> sendMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }
}
