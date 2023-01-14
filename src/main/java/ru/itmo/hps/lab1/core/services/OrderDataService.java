package ru.itmo.hps.lab1.core.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.OrderDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.ProductDto;
import ru.itmo.hls.exception.NotFoundException;
import ru.itmo.hls.exception.PageNotFoundException;
import ru.itmo.hps.lab1.core.entity.Order;
import ru.itmo.hps.lab1.core.entity.Product;
import ru.itmo.hps.lab1.core.repository.CustomizedOrderCrudRepository;
import ru.itmo.hps.lab1.core.repository.CustomizedProductCrudRepository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderDataService {
    private final ModelMapper modelMapper;
    private final CustomizedOrderCrudRepository customizedOrderCrudRepository;
    private final CustomizedProductCrudRepository customizedProductCrudRepository;


    public PageDto getAllOrders(String username, @NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<OrderDto> list = customizedOrderCrudRepository.findAll(pageable).map(i -> modelMapper.map(i, OrderDto.class));
        if (list.isEmpty()) {
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageDto.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }

    public OrderDto addOrder(String username, OrderDto payload) {
        var product = customizedProductCrudRepository.findById(payload.getProductsId());
        if (product.isEmpty()) {
            throw new NotFoundException(String.format("product %s not found", payload.getProductsId()));
        }
        var order = Order.builder()
                .username(username)
                .deliveryInfo(payload.getDeliveryInfo())
                .paymentId(payload.getPaymentId())
                .products(product.get())
                .build();
        return modelMapper.map(customizedOrderCrudRepository.save(order), OrderDto.class);
    }
}

