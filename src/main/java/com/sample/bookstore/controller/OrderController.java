package com.sample.bookstore.controller;

import com.sample.bookstore.Exception.BookNotFoundException;
import com.sample.bookstore.Exception.OrderItemException;
import com.sample.bookstore.Exception.OrderNotFoundException;
import com.sample.bookstore.db.model.BookStoreOrder;
import com.sample.bookstore.db.model.Books;
import com.sample.bookstore.db.model.OrderItem;
import com.sample.bookstore.db.repo.BookRepository;
import com.sample.bookstore.db.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookStoreOrder create(@RequestBody BookStoreOrder order) {
        List<OrderItem> orderItemList = order.getOrderItems();
        if(orderItemList.isEmpty()) {
            throw new OrderItemException("There are no order item added to this order");
        }
        for(OrderItem o : orderItemList) {
            Optional<Books> book = bookRepository.findById(o.getItemId());
            if(book.isEmpty()) {
                throw new BookNotFoundException("Book id : " + o.getItemId() + " not found in store");
            }
            int inventory = book.get().getInventory();
            if(o.getItemQuantity() > inventory) {
                throw new OrderItemException("Insufficient quantity remaining in store for book id : " + o.getItemId());
            }
            Books booksItem = book.get();
            booksItem.setInventory(inventory-o.getItemQuantity());
            bookRepository.save(booksItem);
        }
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
