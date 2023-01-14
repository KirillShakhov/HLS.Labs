package ru.itmo.hps.lab1.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Chat;
import ru.itmo.hps.lab1.chat.entity.Message;
import ru.itmo.hps.lab1.chat.repositories.ChatRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Mono<Chat> getChatById(String username, Long id){
        var chat = chatRepository.findById(id);
        if (chat.isEmpty())
            return Mono.empty();
        if(!chat.get().getUsers().contains(username) && !chat.get().getAdminUser().equals(username))
            return Mono.empty();
        return Mono.fromCallable(() -> chat)
                .flatMap(optional -> optional.map(Mono::just).orElseGet(Mono::empty));
    }

    public Mono<Chat> addChat(String title, String adminUser){
        return Mono.just(chatRepository.save(Chat.builder()
                    .title(title)
                    .adminUser(adminUser)
                    .users(new HashSet<>())
                    .build()));
    }

    public Flux<Chat> getChat(String username) {
        var chats = chatRepository.findAll();
        var list = new ArrayList<Chat>();
        for (var chat : chats){
            if (chat.getUsers().contains(username) || chat.getAdminUser().equals(username)){
                list.add(chat);
            }
        }
        return Flux.fromIterable(list)
                .filter(chat -> chat.getUsers().contains(username));
    }

    public Mono<Chat> sendById(String username, Long id, String text) {
        var chat = chatRepository.findById(id);
        if (chat.isEmpty())
            return Mono.empty();
        if(!chat.get().getUsers().contains(username) && !chat.get().getAdminUser().equals(username))
            return Mono.empty();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        chat.get().getMessages().add(Message
                .builder()
                        .username(username)
                        .date(dtf.format(localDate))
                        .message(text)
                .build());
        return Mono.fromCallable(() -> chatRepository.save(chat.get()));
    }


}
