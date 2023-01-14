package ru.itmo.hls.facade.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.*;
import ru.itmo.hls.exception.NotFoundException;

import javax.validation.Valid;

@FeignClient(value = "product-service")
public interface ProductClient {
    @PostMapping(value = "/api/v1/categories/create")
    CategoryDto addCategory(@RequestHeader("username") String username, @RequestHeader("role") String role, @Valid @RequestBody CategoryDto payload);

    @GetMapping(value = "/api/v1/categories/get")
    PageDto listProduct(@RequestHeader("username") String username, @RequestHeader("role") String role, @RequestParam(value = "page") Integer page);

    @PostMapping(value = "/api/v1/orders/create")
    CategoryDto addOrder(@RequestHeader("username") String username, @RequestHeader("role") String role, @Valid @RequestBody CategoryDto payload);

    @GetMapping(value = "/api/v1/orders/get")
    PageDto listOrders(@RequestHeader("username") String username, @RequestHeader("role") String role, @RequestParam(value = "page") Integer page);

    @PostMapping(value = "/api/v1/products/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto addProduct(@RequestHeader("username") String username, @RequestHeader("role") String role, @Valid @RequestBody ProductDto payload);
    @GetMapping(value = "/api/v1/products/get")
    PageDto getProducts(@RequestHeader("username") String username, @RequestHeader("role") String role, @RequestParam(value = "page") Integer page);
}
