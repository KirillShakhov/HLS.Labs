package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.ChatDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.ProductDto;
import ru.itmo.hls.facade.client.ChatClient;
import ru.itmo.hls.facade.client.ProductClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductClient productClient;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto addProduct(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @Valid @RequestBody ProductDto payload){
        return productClient.addProduct(username, role, payload);
    }

    @GetMapping(value = "/get")
    PageDto getProducts(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @RequestParam(value = "page") Integer page){
        return productClient.getProducts(username, role, page);
    }
}
