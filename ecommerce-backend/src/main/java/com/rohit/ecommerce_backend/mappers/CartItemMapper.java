package com.rohit.ecommerce_backend.mappers;

import com.rohit.ecommerce_backend.dto.CartItemResponseDto;
import com.rohit.ecommerce_backend.entities.CartItem;
import com.rohit.ecommerce_backend.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemResponseDto toDto(CartItem cartItem) {

        Product product = cartItem.getProduct();

        return CartItemResponseDto.builder()
                .id(cartItem.getId())
                .name(product.getName())
                .quantity(cartItem.getQuantity())
                .imageUrl(product.getImageUrl())
                .totalAmount(calculateTotalAmount(product, cartItem.getQuantity()))
                .createdDate(cartItem.getCreatedDate())
                .updatedDate(cartItem.getUpdatedDate())
                .build();
    }

    private Long calculateTotalAmount(Product product, Integer quantity) {
        return product.getPrice() * quantity;
    }

}
