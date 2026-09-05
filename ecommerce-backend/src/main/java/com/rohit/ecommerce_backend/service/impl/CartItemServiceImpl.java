package com.rohit.ecommerce_backend.service.impl;

import com.rohit.ecommerce_backend.dto.AddToCartDto;
import com.rohit.ecommerce_backend.dto.CartItemResponseDto;
import com.rohit.ecommerce_backend.entities.CartItem;
import com.rohit.ecommerce_backend.entities.Product;
import com.rohit.ecommerce_backend.entities.User;
import com.rohit.ecommerce_backend.exceptions.NotFoundException;
import com.rohit.ecommerce_backend.mappers.CartItemMapper;
import com.rohit.ecommerce_backend.repositories.CartItemRepository;
import com.rohit.ecommerce_backend.repositories.ProductRepository;
import com.rohit.ecommerce_backend.service.CartItemService;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final SecurityUtils securityUtils;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartItemResponseDto addToCart(AddToCartDto addToCartDto) {

        log.info("Inside the addToCart () : ");

        User user = securityUtils.getCurrentUser();

        Optional<CartItem> existingItem = cartItemRepository.findCartItemByUserIdAndProductId(user.getId(),addToCartDto.getProductId());

        if(existingItem.isPresent()){

            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + addToCartDto.getQuantity());

            CartItem savedCartItem = cartItemRepository.save(item);

            return cartItemMapper.toDto(savedCartItem);

        }else {

            Product product = productRepository.findById(addToCartDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found with id : " + addToCartDto.getProductId()) );

            CartItem cartItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(addToCartDto.getQuantity())
                    .build();

            CartItem savedCartItem = cartItemRepository.save(cartItem);

            return cartItemMapper.toDto(savedCartItem);

        }


    }

    @Override
    @Transactional
    public List<CartItemResponseDto> getAllCartsItem() {

        User user = securityUtils.getCurrentUser();

        List<CartItem> cartItems = cartItemRepository.findCartItemsByUserId(user.getId());


        return cartItems.stream()
                .map(cartItemMapper::toDto)
                .toList();
    }

    @Override
    public void deleteCartItem(Long id) {

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Cart Item not found with Id : "+ id) );

        cartItemRepository.delete(cartItem);

    }
}
