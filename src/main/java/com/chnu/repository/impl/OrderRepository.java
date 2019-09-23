package com.chnu.repository.impl;

import com.chnu.model.Order;
import com.chnu.repository.IOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends AbstractRepository<Order, Long> implements IOrderRepository {
}
