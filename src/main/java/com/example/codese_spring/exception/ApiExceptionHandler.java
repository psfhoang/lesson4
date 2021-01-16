package com.example.codese_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public String invalidInputException(Exception e, WebRequest request) {
//        return "Thiếu input đầu vào";
//    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFound(ResourceNotFoundException exception,WebRequest request){
        return "không tìm thấy tài nguyên";
    }
    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String already(ResourceAlreadyExistException e,WebRequest request){
        return "đã tồn tại";
    }

}
