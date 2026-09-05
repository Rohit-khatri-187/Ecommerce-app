package com.rohit.ecommerce_backend.exceptions;

import com.rohit.ecommerce_backend.utils.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionResponse> handleAllException(Exception ex){
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex){
        return buildErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    private ResponseEntity<ExceptionResponse> handleAlreadyExistException(AlreadyExistException ex){
        return buildErrorResponse(HttpStatus.CONFLICT,ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialException.class)
    private ResponseEntity<ExceptionResponse> handleInvalidCredentialException(InvalidCredentialException ex){
        return buildErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    private ResponseEntity<ExceptionResponse> handleNotAuthenticatedException(NotAuthenticatedException ex){
        return buildErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
    }


    private ResponseEntity<ExceptionResponse> buildErrorResponse(
            HttpStatus status ,
            String message
    ){
        ExceptionResponse response= ExceptionResponse.builder()
                                    .status(status.value())
                                    .message(message)
                                    .success(false)
                                    .build();

        return ResponseEntity.status(status)
                .body(response);
    }

}
