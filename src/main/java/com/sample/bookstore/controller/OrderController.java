package com.sample.bookstore.controller;

import com.sample.bookstore.Exception.OrderNotFoundException;
import com.sample.bookstore.db.model.BookStoreOrder;
import com.sample.bookstore.db.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public Iterable findAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookStoreOrder create(@RequestBody BookStoreOrder order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public Optional<BookStoreOrder> findById(@PathVariable Long id) {
        Optional<BookStoreOrder> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with id : " + id + " not found");
        }
        return optionalOrder;
    }
}
