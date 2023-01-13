package ru.itmo.hps.lab1.chat.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.itmo.hps.lab1.chat.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {

    @Query("SELECT * FROM message m WHERE m.chat_id = :id")
    Flux<Message> getMessageByChatId(Long id);
}
