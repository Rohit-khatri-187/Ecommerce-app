package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.AddToCartDto;
import com.rohit.ecommerce_backend.dto.CartItemResponseDto;
import com.rohit.ecommerce_backend.repositories.CartItemRepository;
import com.rohit.ecommerce_backend.service.CartItemService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/carts")
@RestController
public class CartItemController extends BaseController {

    private final CartItemService cartItemService;
    private final SecurityUtils securityUtils;
    private final CartItemRepository cartItemRepository;

    @PostMapping
    public ResponseEntity<BaseResponse<CartItemResponseDto>> addToCart(@RequestBody AddToCartDto addToCartDto){
        CartItemResponseDto response = cartItemService.addToCart(addToCartDto);

        return buildResponse(response,"Item added successfully");
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CartItemResponseDto>>> getAllCartsItem(){
        List<CartItemResponseDto> response = cartItemService.getAllCartsItem();

        return buildResponse(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCartItem(@PathVariable Long id){

        cartItemService.deleteCartItem(id);

        return buildResponse(null,"Item deleted successfully");
    }


}
