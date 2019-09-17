package com.chnu.controller;

import com.chnu.model.Courier;
import com.chnu.service.ICourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courier")
public class CourierController {
    private final ICourierService courierService;

    @Autowired
    public CourierController(ICourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping("")
    public Courier save(@RequestBody Courier courier) {
        return courierService.save(courier).orElse(null);
    }

    @GetMapping("/{id}")
    public Courier findById(@PathVariable(name = "id", required = true) Long pk) {
        return courierService.findById(pk).orElse(null);
    }

    @PutMapping("")
    public Courier update(@RequestBody Courier object) {
        return courierService.update(object);
    }

    @DeleteMapping("")
    public void delete(@RequestBody Courier object) {
        courierService.delete(object);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id", required = true) Long pk) {
        courierService.deleteById(pk);
    }

    @GetMapping("")
    public List<Courier> findAll() {
        return courierService.findAll();
    }
}
