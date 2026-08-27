package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.PagedResponse;
import com.rohit.ecommerce_backend.entities.Product;
import com.rohit.ecommerce_backend.service.ProductService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PagedResponse<Product>> getAllProducts(
            @PageableDefault(page = 0, size = 20) Pageable pageable) {

        PagedResponse<Product> response = productService.getAllProducts(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable @PathParam("id") Long id ) {

        Product response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

}
