package com.rohit.ecommerce_backend.service.impl;

import com.rohit.ecommerce_backend.dto.PagedResponse;
import com.rohit.ecommerce_backend.entities.Product;
import com.rohit.ecommerce_backend.exceptions.NotFoundException;
import com.rohit.ecommerce_backend.repositories.ProductRepository;
import com.rohit.ecommerce_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PagedResponse<Product> getAllProducts(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);

        System.out.println(productPage);

        List<Product> products = productPage.stream().toList();

        return PagedResponse.<Product>builder()
                .content(products)
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .isLast(productPage.isLast())
                .build();
    }

    @Override
    public Product getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Product not fount with Id "+ id) );

        return product;
    }

}
