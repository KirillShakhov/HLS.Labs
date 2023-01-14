package ru.itmo.hps.lab1.attachment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.attachment.entity.Attachment;
import ru.itmo.hps.lab1.attachment.services.AttachmentService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final AttachmentService attachmentService;

    @GetMapping("/get/{id}")
    private Mono<Attachment> getAttachmentById(@PathVariable Long id) {
        return attachmentService.getAttachmentById(id);
    }

}
