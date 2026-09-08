package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.AddToOrderRequestDto;
import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;
import com.rohit.ecommerce_backend.service.OrderService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "Order Endpoints" , description = "These endpoints are related to order")
@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController extends BaseController {

    private final OrderService orderService;

    @Operation(
            summary = "Place an Order",
            description = "This is a endpoint to place an order"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<List<OrderItemResponseDto>>> placeOrder(@RequestBody AddToOrderRequestDto orderRequest){

        List<OrderItemResponseDto> placedOrder = orderService.placeOrder(orderRequest);

        return buildResponse(placedOrder,"Order is placed successfully");
    }


}
