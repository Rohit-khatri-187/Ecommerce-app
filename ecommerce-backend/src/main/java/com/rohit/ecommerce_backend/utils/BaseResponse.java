package com.rohit.ecommerce_backend.utils;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private T content;
    private boolean success;
    private String message;

    public BaseResponse(T content) {
        this.content = content;
        this.success = true;
        this.message = "Successful";
    }

    public BaseResponse(T content, String message) {
        this.content = content;
        this.success = true;
        this.message = message;
    }


}
