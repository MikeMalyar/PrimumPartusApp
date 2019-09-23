package com.chnu.service;

import com.chnu.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Optional<Order> save(Order order);

    Optional<Order> findById(Long pk);

    Order update(Order object);

    boolean existsById(Long pk);

    void deleteById(Long pk);

    List<Order> findAll();

    List<Order> findWithPagination(int first, int count);
}
