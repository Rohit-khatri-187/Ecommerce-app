package com.rohit.ecommerce_backend.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ExceptionResponse {

    private int status;
    private String message;
    private Boolean success;

}
