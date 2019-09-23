package com.chnu.service.impl;

import com.chnu.model.Courier;
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

    public Optional<Order> save(Order order) {
        if (!findById(order.getOrderId()).isPresent()) return Optional.ofNullable(orderRepository.save(order));
        return Optional.empty();
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public Order update(Order object){
        return orderRepository.update(object);
    }

    public boolean existsById(Long pk){
        return orderRepository.existsById(pk);
    }

    public void deleteById(Long pk){
        orderRepository.deleteById(pk);
    }

    public void delete(Order entity){
        orderRepository.delete(entity);
    }

    public List<Order> findWithPagination(int first, int count){
        return orderRepository.findWithPagination(first, count);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
