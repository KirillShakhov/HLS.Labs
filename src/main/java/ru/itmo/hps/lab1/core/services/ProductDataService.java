package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Attachment;
import ru.itmo.hps.lab1.core.entity.PageEntity;
import ru.itmo.hps.lab1.core.entity.Product;
import ru.itmo.hps.lab1.core.exeptions.NotFoundException;
import ru.itmo.hps.lab1.core.exeptions.PageNotFoundException;
import ru.itmo.hps.lab1.core.repository.CustomizedAttachmentCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedCategoryCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedProductCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedUserCrudRepository;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductDataService {
    private final CustomizedProductCrudRepository customizedProductCrudRepository;
    private final CustomizedUserCrudRepository customizedUserCrudRepository;
    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;
    private final CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository;

    @Autowired
    public ProductDataService(CustomizedProductCrudRepository customizedProductCrudRepository, CustomizedUserCrudRepository customizedUserCrudRepository, CustomizedCategoryCrudRepository customizedCategoryCrudRepository, CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository) {
        this.customizedProductCrudRepository = customizedProductCrudRepository;
        this.customizedUserCrudRepository = customizedUserCrudRepository;
        this.customizedCategoryCrudRepository = customizedCategoryCrudRepository;
        this.customizedAttachmentCrudRepository = customizedAttachmentCrudRepository;
    }

    public List<Product> findAll() {
        return (List<Product>) customizedProductCrudRepository.findAll();
    }

    public void save(Product product) {
        customizedProductCrudRepository.save(product);
    }

    public void removeById(Long id) {
        customizedProductCrudRepository.deleteById(id);
    }

    public Optional<Product> getById(Long id) {
        return customizedProductCrudRepository.findById(id);
    }

    public void add(String username, String name, Long categoryId, String description,List<Long> attachment) {
        var user = customizedUserCrudRepository.findByUsername(username);
        if (user.isEmpty()) throw new NotFoundException(String.format("username %s not found", username));
        var category = customizedCategoryCrudRepository.findById(categoryId);
        if (category.isEmpty()) throw new NotFoundException(String.format("category %s not found", categoryId));
        Set<Attachment> attachments = new HashSet<>();
        for (Long attachmentId : attachment){
            var a = customizedAttachmentCrudRepository.findById(attachmentId);
            if (a.isEmpty()) throw new NotFoundException(String.format("attachment %s not found", attachmentId));
            attachments.add(a.get());
        }
        var product = Product.builder()
                                        .name(name)
                                        .user(user.get())
                                        .category(category.get())
                                        .description(description)
                                        .attachments(attachments)
                                        .build();
        customizedProductCrudRepository.save(product);
    }

    public PageEntity getAllAttachment(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Product> list = customizedProductCrudRepository.findAll(pageable);
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageEntity.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }

}

