package com.springhibernateenvers.lb.controller;

import com.springhibernateenvers.lb.utils.exceptions.DataNotFoundException;
import com.springhibernateenvers.lb.utils.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> dataNotFoundException(final DataNotFoundException ex, final HttpServletRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setCallerUrl(request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}