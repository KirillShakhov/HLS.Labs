package ru.itmo.hps.lab1.attachment.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hps.lab1.attachment.entity.Attachment;
import ru.itmo.hps.lab1.attachment.entity.AttachmentType;
import ru.itmo.hps.lab1.attachment.repositories.AttachmentRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final ModelMapper modelMapper;

    private final AttachmentRepository attachmentRepository;

    public Mono<AttachmentDto> addAttachment(@NonNull String base64, @NonNull AttachmentType type) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        return attachmentRepository.save(Attachment.builder()
                .base64(base64)
                .type(type)
                .createDate(dtf.format(localDate))
                .build()).map(marketInstrumentId -> modelMapper.map(marketInstrumentId, AttachmentDto.class));
    }

    public Mono<AttachmentDto> getAttachmentById(Long id) {
        return attachmentRepository.findById(id).map(marketInstrumentId -> modelMapper.map(marketInstrumentId, AttachmentDto.class));
    }

    public Flux<AttachmentDto> getAttachments(@NonNull Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 50);
        return attachmentRepository.findAllBy(pageable)
                .map(marketInstrumentId -> modelMapper.map(marketInstrumentId, AttachmentDto.class));
    }
}
