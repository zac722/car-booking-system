package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Car;
import com.example.backend.request.CarRequest;
import com.example.backend.service.CarInventoryService;

@RestController
@RequestMapping("/api/inventory/cars")
public class CarInventoryController {

    @Autowired
    private CarInventoryService carInventoryService;

    @GetMapping
    public List<Car> getAllCars() {
        return carInventoryService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carInventoryService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Car createCar(@RequestBody CarRequest carRequest) {
        return carInventoryService.saveCar(carRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return ResponseEntity.ok(carInventoryService.updateCar(id, carRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        return carInventoryService.getCarById(id)
                .map(car -> {
                    carInventoryService.deleteCar(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
