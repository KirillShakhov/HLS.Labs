package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CategoryDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.facade.client.ProductClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final ProductClient orderClient;

    @PostMapping(value = "/create")
    CategoryDto addOrder(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @Valid @RequestBody CategoryDto payload){
        return orderClient.addOrder(username, role, payload);
    }

    @GetMapping(value = "/get")
    PageDto listOrders(@RequestAttribute("username") String username, @RequestAttribute("role") String role, @RequestParam(value = "page") Integer page){
        return orderClient.listOrders(username, role, page);
    }
}
