package ru.itmo.hls.facade.client.fallback;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.entity.AttachmentType;
import ru.itmo.hls.facade.client.AttachmentClient;
import ru.itmo.hls.facade.client.AuthClient;

@Component
public class AttachmentClientFallback implements AttachmentClient {
    @Override
    public Mono<AttachmentDto> createAttachment(String base64, AttachmentType type) {
        return Mono.empty();
    }

    @Override
    public Flux<AttachmentDto> getAttachment(Integer page) {
        return Flux.empty();
    }

    @Override
    public Mono<AttachmentDto> getAttachmentById(Long id) {
        return Mono.empty();
    }
}
