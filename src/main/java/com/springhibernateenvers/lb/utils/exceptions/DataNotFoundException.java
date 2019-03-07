package com.springhibernateenvers.lb.utils.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }
}
