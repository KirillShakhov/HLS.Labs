package ru.itmo.hps.lab1.chat.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Chat;

@Repository
public interface ChatRepository extends ReactiveCrudRepository<Chat, Long> {
    @Query("SELECT * FROM chat c WHERE c.id = :id")
    Mono<Chat> findById(Long id);

    @Query("SELECT EXISTS(SELECT * FROM chat c WHERE c.id = :id)")
    Mono<Boolean> existsChatById(Long id);
}
