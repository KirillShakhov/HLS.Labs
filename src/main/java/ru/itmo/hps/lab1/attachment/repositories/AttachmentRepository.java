package ru.itmo.hps.lab1.attachment.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.attachment.entity.Attachment;

@Repository
public interface AttachmentRepository extends ReactiveCrudRepository<Attachment, Long> {
    Mono<Attachment> findById(Long id);

    Flux<Attachment> findAllBy(Pageable pageable);
}
