package com.chnu.controller;

import com.chnu.model.Courier;
import com.chnu.rest.GenericResponse;
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
    public GenericResponse<Courier> save(@RequestBody Courier courier) {
        GenericResponse<Courier> response = GenericResponse
                .of(courierService.save(courier).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error("This courier already exist.");
    }

    @GetMapping("/{id}")
    public GenericResponse<Courier> findById(@PathVariable(name = "id", required = true) Long pk) {
        GenericResponse<Courier> response = GenericResponse
                .of(courierService.findById(pk).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error("Courier not found with id " + pk);
    }

    @PutMapping("")
    public GenericResponse<Courier> update(@RequestBody Courier object) {
        if(object != null) {
            return GenericResponse.of(courierService.update(object));
        }
        return GenericResponse.error("Courier is null.");
    }

    @DeleteMapping("")
    public GenericResponse<Void> delete(@RequestBody Courier object) {
        courierService.delete(object);
        return GenericResponse.empty();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteById(@PathVariable(name = "id", required = true) Long pk) {
        courierService.deleteById(pk);
        return GenericResponse.empty();
    }

    @GetMapping("")
    public GenericResponse<List<Courier>> findAll() {
        return GenericResponse.of(courierService.findAll());
    }
}
