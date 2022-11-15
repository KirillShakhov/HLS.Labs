package ru.itmo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itmo.entity.InstaUserDetails;
import ru.itmo.entity.View;
import ru.itmo.exeptions.PageNotFoundException;
import ru.itmo.payload.ApiResponse;
import ru.itmo.payload.CategoryRequest;
import ru.itmo.payload.ProductRequest;
import ru.itmo.services.AttachmentDataService;
import ru.itmo.services.ProductDataService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductDataService productDataService;

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addProduct(@AuthenticationPrincipal InstaUserDetails userDetails, @Valid @RequestBody ProductRequest payload) {
        try {
            productDataService.add(userDetails.getUsername(), payload.getName(), payload.getCategory(), payload.getDescription(), payload.getAttachment());
            return ResponseEntity.ok(new ApiResponse(true, "Product has been added"));
        }
        catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.toString()));
        }
    }

    @JsonView(View.PageEntity.class)
    @GetMapping(value = "/product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listProduct(@RequestParam(value = "page") Integer page)  {
        try {
            log.info("retrieving all attachments");
            return ResponseEntity
                    .ok(productDataService.getAllAttachment(page));
        }
        catch (PageNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
        }
    }
}
