package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hps.lab1.core.entity.Category;
import ru.itmo.hps.lab1.core.exeptions.AlreadyExistsException;
import ru.itmo.hps.lab1.core.exeptions.PageNotFoundException;
import ru.itmo.hps.lab1.core.repository.CustomizedCategoryCrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryDataService {
    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;

    @Autowired
    public CategoryDataService(CustomizedCategoryCrudRepository customizedCategoryCrudRepository) {
        this.customizedCategoryCrudRepository = customizedCategoryCrudRepository;
    }

    public List<Category> findAll() {
        return customizedCategoryCrudRepository.findAll();
    }

    public void add(Category category) throws AlreadyExistsException {
        if (customizedCategoryCrudRepository.existsByName(category.getName())) throw new AlreadyExistsException(String.format("Category %s already exists", category.getName()));
        customizedCategoryCrudRepository.save(category);
    }

    public void removeById(Long id) {
        customizedCategoryCrudRepository.deleteById(id);
    }

    public Optional<Category> getById(Long id) {
        return customizedCategoryCrudRepository.findById(id);
    }

    public PageDto getAllCategories(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Category> list = customizedCategoryCrudRepository.findAll(pageable);
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageDto.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }
}

