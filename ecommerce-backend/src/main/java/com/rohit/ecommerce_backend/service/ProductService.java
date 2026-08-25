package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.PagedResponse;
import com.rohit.ecommerce_backend.entities.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    PagedResponse<Product> getAllProducts(Pageable pageable);

    Product getProductById(Long id);

}
