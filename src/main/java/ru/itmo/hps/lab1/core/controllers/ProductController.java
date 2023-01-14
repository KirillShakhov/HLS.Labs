package ru.itmo.hps.lab1.core.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.AttachmentDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.exception.NotFoundException;
import ru.itmo.hls.exception.PageNotFoundException;
import ru.itmo.hls.dto.ProductDto;
import ru.itmo.hps.lab1.core.services.ProductDataService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDataService productDataService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto addProduct(@RequestHeader("username") String username, @RequestHeader("role") String role, @Valid @RequestBody ProductDto payload) {
        if (!role.equals("SELLER")){
            throw new NotFoundException("You are not a seller");
        }
        return productDataService.addProduct(username, payload);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public PageDto getProducts(@RequestHeader("username") String username, @RequestHeader("role") String role, @RequestParam(value = "page") Integer page)  {
        if (!role.equals("SELLER")){
            throw new NotFoundException("You are not a seller");
        }
        return productDataService.getAllProduct(page);
    }
}
