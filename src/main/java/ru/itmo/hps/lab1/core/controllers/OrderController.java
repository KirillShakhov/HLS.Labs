package ru.itmo.hps.lab1.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.OrderDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hps.lab1.core.services.OrderDataService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderDataService orderDataService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderDto addOrder(@RequestHeader("username") String username, @Valid @RequestBody OrderDto payload) {
        return orderDataService.addOrder(username, payload);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public PageDto getOrders(@RequestHeader("username") String username, @RequestParam(value = "page") Integer page)  {
        return orderDataService.getAllOrders(username, page);
    }
}
