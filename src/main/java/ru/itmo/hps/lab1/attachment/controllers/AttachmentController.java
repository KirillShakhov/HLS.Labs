package ru.itmo.hps.lab1.attachment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hps.lab1.attachment.entity.Attachment;
import ru.itmo.hps.lab1.attachment.entity.AttachmentType;
import ru.itmo.hps.lab1.attachment.service.AttachmentService;

@RestController
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/create")
    private ResponseEntity<Mono<AttachmentDto>> createAttachment(@RequestParam String base64, @RequestParam AttachmentType type) {
        return ResponseEntity.ok(attachmentService.addAttachment(base64, type));
    }

    @GetMapping("/get")
    private ResponseEntity<Flux<AttachmentDto>> getAttachment(@RequestParam Integer page) {
        return ResponseEntity.ok(attachmentService.getAttachments(page));
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<Mono<AttachmentDto>> getAttachmentById(@PathVariable Long id) {
        return ResponseEntity.ok(attachmentService.getAttachmentById(id));
    }
}
