package ru.itmo.hps.lab1.core.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.CategoryDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.ProductDto;
import ru.itmo.hps.lab1.core.entity.Category;
import ru.itmo.hls.exception.AlreadyExistsException;
import ru.itmo.hls.exception.PageNotFoundException;
import ru.itmo.hps.lab1.core.repository.CustomizedCategoryCrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryDataService {
    private final ModelMapper modelMapper;

    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;


    public CategoryDto add(CategoryDto category) throws AlreadyExistsException {
        if (customizedCategoryCrudRepository.existsByName(category.getName())) throw new AlreadyExistsException(String.format("Category %s already exists", category.getName()));
        return modelMapper.map(customizedCategoryCrudRepository.save(Category.builder()
                .name(category.getName())
                .build()), CategoryDto.class);
    }

    public PageDto getAllCategories(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<CategoryDto> list = customizedCategoryCrudRepository.findAll(pageable).map(i -> modelMapper.map(i, CategoryDto.class));
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageDto.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }
}

