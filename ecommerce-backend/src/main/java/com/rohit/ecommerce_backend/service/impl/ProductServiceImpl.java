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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PagedResponse<List<Product>> getAllProducts(Pageable pageable) {

        System.out.println("Inside getAllProducts() : ");

        Page<Product> productPage = productRepository.findAll(pageable);

        List<Product> products = productPage.stream().toList();

        return PagedResponse.<List<Product>>builder()
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

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateStockAfterOrderItemCancellation(Long productId , Integer addedQuantity){
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new NotFoundException("Product not fount with Id "+ productId) );

        product.setStock(product.getStock() + addedQuantity);
        productRepository.save(product);
    }

}
