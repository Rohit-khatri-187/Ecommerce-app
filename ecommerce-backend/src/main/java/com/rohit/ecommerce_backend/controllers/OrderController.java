package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.AddToOrderRequestDto;
import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;
import com.rohit.ecommerce_backend.entities.OrderItem;
import com.rohit.ecommerce_backend.service.OrderService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "200", description = "successful order place")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<List<OrderItemResponseDto>>> placeOrder(@RequestBody AddToOrderRequestDto orderRequest){

        List<OrderItemResponseDto> placedOrder = orderService.placeOrder(orderRequest);

        return buildResponse(placedOrder,"Order is placed successfully");
    }



    @Operation(
            summary = "Cancel and Order ",
            description = "This is a endpoint to cancel an Order "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful order cancellation")
    })
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<BaseResponse<Void>> cancelAnOrder(@PathVariable Long orderItemId){

       orderService.cancelOrderItem(orderItemId);

        return buildResponse(null,"Order is placed successfully");
    }



    @Operation(
            summary = "Get all order Items ",
            description = "This is a endpoint to get all placed order Items "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful order retrival")
    })
    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderItemResponseDto>>> getAllOrderItems(){

         List<OrderItemResponseDto> orderItems =  orderService.getAllOrderItems();

        return buildResponse(orderItems);
    }


}
