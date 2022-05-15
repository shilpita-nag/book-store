package com.sample.bookstore.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookStoreExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AuthorNotFoundException.class})
    protected ResponseEntity<Object> handleAuthorNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({BookNotFoundException.class})
    protected ResponseEntity<Object> handleBookNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    protected ResponseEntity<Object> handleCustomerNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e,e.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
