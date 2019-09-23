package com.chnu.controller;

import com.chnu.model.Order;
import com.chnu.rest.GenericResponse;
import com.chnu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public GenericResponse<Order> save(@RequestBody Order order) {
        GenericResponse<Order> response = GenericResponse
                .of(orderService.save(order).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error("This order already exist.");
    }

    @GetMapping("/{id}")
    public GenericResponse<Order> findById(@PathVariable(name = "id", required = true) Long pk) {
        GenericResponse<Order> response = GenericResponse
                .of(orderService.findById(pk).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error("Order not found with id " + pk);
    }

    @PutMapping("")
    public GenericResponse<Order> update(@RequestBody Order object) {
        if (object != null) {
            return GenericResponse.of(orderService.update(object));
        }
        return GenericResponse.error("Order is null.");
    }

    @DeleteMapping("")
    public GenericResponse<Void> delete(@RequestBody Order object) {
        orderService.delete(object);
        return GenericResponse.empty();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteById(@PathVariable(name = "id", required = true) Long pk) {
        orderService.deleteById(pk);
        return GenericResponse.empty();
    }

    @GetMapping("")
    public GenericResponse<List<Order>> findAll() {
        return GenericResponse.of(orderService.findAll());
    }
}
