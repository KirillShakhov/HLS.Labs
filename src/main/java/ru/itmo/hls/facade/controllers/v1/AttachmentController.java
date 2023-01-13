package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hls.entity.AttachmentType;
import ru.itmo.hls.facade.client.AttachmentClient;
import ru.itmo.hls.facade.client.ChatClient;


@RestController
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentClient attachmentClient;

    @PostMapping("/create")
    Mono<AttachmentDto> createAttachment(@RequestParam String base64, @RequestParam AttachmentType type){
        return attachmentClient.createAttachment(base64, type);
    }

    @GetMapping("/get")
    Flux<AttachmentDto> getAttachment(@RequestParam Integer page){
        return attachmentClient.getAttachment(page);
    }

    @GetMapping("/create")
    Mono<AttachmentDto> getAttachmentById(@PathVariable Long id){
        return attachmentClient.getAttachmentById(id);
    }
}
