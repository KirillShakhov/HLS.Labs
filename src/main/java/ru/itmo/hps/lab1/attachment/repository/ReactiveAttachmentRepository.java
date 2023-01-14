package ru.itmo.hps.lab1.attachment.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.attachment.entity.Attachment;

@Repository
public interface ReactiveAttachmentRepository extends ReactiveCrudRepository<Attachment, String> {

    Mono<Attachment> findById(Long id);
}
