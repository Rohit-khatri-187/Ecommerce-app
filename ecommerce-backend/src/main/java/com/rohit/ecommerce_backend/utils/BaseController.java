package com.rohit.ecommerce_backend.utils;

import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<BaseResponse<T>> buildResponse(T data) {
        return ResponseEntity.ok(
                new BaseResponse<>(data)
        );
    }

    protected <T> ResponseEntity<BaseResponse<T>> buildResponse(
            T data,
            String message
    ) {
        return ResponseEntity.ok(
                new BaseResponse<>(data, message)
        );
    }


}
