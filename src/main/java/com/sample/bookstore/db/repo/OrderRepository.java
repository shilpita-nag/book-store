package com.sample.bookstore.db.repo;

import com.sample.bookstore.db.model.BookStoreOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<BookStoreOrder, Long> {
}

