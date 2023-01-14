package ru.itmo.hps.lab1.core.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.ProductDto;
import ru.itmo.hps.lab1.core.entity.Product;
import ru.itmo.hls.exception.NotFoundException;
import ru.itmo.hls.exception.PageNotFoundException;
import ru.itmo.hps.lab1.core.repository.CustomizedCategoryCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedProductCrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductDataService {
    private final ModelMapper modelMapper;

    private final CustomizedProductCrudRepository customizedProductCrudRepository;
    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;

    public PageDto getAllProduct(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<ProductDto> list = customizedProductCrudRepository.findAll(pageable).map(i -> modelMapper.map(i, ProductDto.class));
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageDto.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }

    public ProductDto addProduct(String username, ProductDto payload) {
        var category = customizedCategoryCrudRepository.findByName(payload.getCategory());
        if (category.isEmpty()) throw new NotFoundException(String.format("category %s not found", payload.getCategory()));
        var product = Product.builder()
                .name(payload.getName())
                .username(username)
                .description(payload.getDescription())
                .category(category.get())
                .attachments(payload.getAttachment())
                .build();
        return modelMapper.map(customizedProductCrudRepository.save(product), ProductDto.class);
    }
}

