package com.sample.bookstore.db.repo;

import com.sample.bookstore.db.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
