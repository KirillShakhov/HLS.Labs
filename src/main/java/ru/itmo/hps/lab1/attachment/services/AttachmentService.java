package ru.itmo.hps.lab1.attachment.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.attachment.entity.Attachment;
import ru.itmo.hps.lab1.attachment.repository.ReactiveAttachmentRepository;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final ReactiveAttachmentRepository repository;

    public Mono<Attachment> getAttachmentById(Long id) {
        Mono<Attachment> result = repository.findById(id);
        return result;
    }
}
