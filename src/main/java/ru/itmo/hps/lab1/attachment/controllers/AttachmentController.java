package ru.itmo.hps.lab1.attachment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.attachment.entity.Attachment;
import ru.itmo.hps.lab1.attachment.entity.AttachmentType;
import ru.itmo.hps.lab1.attachment.service.AttachmentService;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/create")
    private ResponseEntity<Mono<Attachment>> createAttachment(@RequestParam String base64, @RequestParam AttachmentType type) {
        return ResponseEntity.ok(attachmentService.addAttachment(base64, type));
    }

    @GetMapping("/get")
    private ResponseEntity<Flux<Attachment>> getAttachment(@RequestParam Integer page) {
        return ResponseEntity.ok(attachmentService.getAttachments(page));
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<Mono<Attachment>> getAttachmentById(@PathVariable Long id) {
        return ResponseEntity.ok(attachmentService.getAttachmentById(id));
    }
}
