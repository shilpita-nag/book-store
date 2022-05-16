package com.sample.bookstore.Exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){super(message);}
}
