package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.AddToCartDto;
import com.rohit.ecommerce_backend.dto.CartItemResponseDto;
import com.rohit.ecommerce_backend.repositories.CartItemRepository;
import com.rohit.ecommerce_backend.service.CartItemService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart Endpoints" , description = "This is a Cart endpoints where user can add delete the cart items")
@RequiredArgsConstructor
@RequestMapping("/carts")
@RestController
public class CartItemController extends BaseController {

    private final CartItemService cartItemService;
    private final SecurityUtils securityUtils;
    private final CartItemRepository cartItemRepository;

    @Operation(
            summary = "Add to cart ",
            description = "Adds products to the cart Items "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CartItemResponseDto>> addToCart(@RequestBody AddToCartDto addToCartDto){
        CartItemResponseDto response = cartItemService.addToCart(addToCartDto);

        return buildResponse(response,"Item added successfully");
    }


    @Operation(
            summary = "Get all cart Items",
            description = "Fetches all the cart Items of the user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<BaseResponse<List<CartItemResponseDto>>> getAllCartsItem(){
        List<CartItemResponseDto> response = cartItemService.getAllCartsItem();

        return buildResponse(response);
    }


    @Operation(
            summary = "Delete Cart Item",
            description = "deletes the cart Item by cart Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCartItem(@PathVariable Long id){

        cartItemService.deleteCartItem(id);

        return buildResponse(null,"Item deleted successfully");
    }


}
