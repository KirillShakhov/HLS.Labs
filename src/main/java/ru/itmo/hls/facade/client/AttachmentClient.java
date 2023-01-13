package ru.itmo.hls.facade.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hls.entity.AttachmentType;

@ReactiveFeignClient(name = "attachment-service")
public interface AttachmentClient {
    @PostMapping("/api/v1/attachment/create")
    Mono<AttachmentDto> createAttachment(@RequestParam String base64, @RequestParam AttachmentType type);

    @GetMapping("/api/v1/attachment/get")
    Flux<AttachmentDto> getAttachment(@RequestParam Integer page);

    @GetMapping("/api/v1/attachment/get/{id}")
    Mono<AttachmentDto> getAttachmentById(@PathVariable Long id);
}
