package ru.itmo.hps.lab1.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CategoryDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.exception.NotFoundException;
import ru.itmo.hps.lab1.core.services.CategoryDataService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDataService categoryDataService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto addCategory(@RequestHeader("username") String username, @RequestHeader("role") String role, @Valid @RequestBody CategoryDto payload) {
        if (!role.equals("Seller")) {
            throw new NotFoundException("You are not a seller");
        }
        return categoryDataService.add(payload);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public PageDto listProduct(@RequestHeader("username") String username, @RequestHeader("role") String role, @RequestParam(value = "page") Integer page) {
        if (!role.equals("Seller")) {
            throw new NotFoundException("You are not a seller");
        }
        return categoryDataService.getAllCategories(page);
    }
}
