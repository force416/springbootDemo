package org.eric.telegrambots.controller;

import org.eric.telegrambots.utils.JSend;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JSend handle(Exception e) {
        e.printStackTrace();
        return JSend.error("Internal server error");
    }

}
