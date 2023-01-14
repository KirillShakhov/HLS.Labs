package ru.itmo.hps.lab1.chat.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Message;
import ru.itmo.hps.lab1.chat.repositories.ChatRepository;
import ru.itmo.hps.lab1.chat.repositories.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public Flux<Message> getMessagesByChatId(Long id){
        return Flux.fromIterable(messageRepository.getMessageByChatId(id));
    }

    public Mono<Boolean> addMessage(Message message) {
        var result = chatRepository.existsChatById(message.getChat_id());
        if (result) {
            messageRepository.save(message);
            return Mono.just(true);
        }
        return Mono.just(false);
    }
}
