package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CategoryDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.facade.client.ProductClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final ProductClient categoryClient;

    @PostMapping(value = "/create")
    CategoryDto addCategory(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @Valid @RequestBody CategoryDto payload){
        return categoryClient.addCategory(username, role, payload);
    }

    @GetMapping(value = "/get")
    PageDto listProduct(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @RequestParam(value = "page") Integer page){
        return categoryClient.listProduct(username, role, page);
    }
}
