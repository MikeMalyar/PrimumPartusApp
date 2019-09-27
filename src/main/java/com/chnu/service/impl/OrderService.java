package com.chnu.service.impl;

import com.chnu.model.Order;
import com.chnu.repository.IOrderRepository;
import com.chnu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> save(Order order) {
        if (!findById(order.getOrderId()).isPresent()) return Optional.ofNullable(orderRepository.save(order));
        return Optional.empty();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order update(Order object) {
        return orderRepository.update(object);
    }

    @Override
    public boolean existsById(Long pk) {
        return orderRepository.existsById(pk);
    }

    @Override
    public void deleteById(Long pk) {
        orderRepository.deleteById(pk);
    }

    @Override
    public List<Order> findWithPagination(int first, int count) {
        return orderRepository.findWithPagination(first, count);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
