package ru.itmo.hps.lab1.chat.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.entity.Chat;

import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    Optional<Chat> findById(Long id);
    Boolean existsChatById(Long id);
}
