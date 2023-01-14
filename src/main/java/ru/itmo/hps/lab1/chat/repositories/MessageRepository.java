package ru.itmo.hps.lab1.chat.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.itmo.hps.lab1.chat.entity.Message;

import java.util.List;
import java.util.Optional;


public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> getMessageByChatId(Long id);
}
