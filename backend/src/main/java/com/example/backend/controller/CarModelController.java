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

import com.example.backend.entity.CarModel;
import com.example.backend.service.CarInventoryService;

@RestController
@RequestMapping("/api/inventory/models")
public class CarModelController {

    @Autowired
    private CarInventoryService carInventoryService;

    @GetMapping
    public List<CarModel> getAllCarModels() {
        return carInventoryService.getAllCarModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarModelById(@PathVariable Long id) {
        return carInventoryService.getCarModelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarModel createCarModel(@RequestBody CarModel carModel) {
        return carInventoryService.saveCarModel(carModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarModel(@PathVariable Long id, @RequestBody CarModel carModel) {
        return carInventoryService.getCarModelById(id)
                .map(existingCarModel -> {
                    carModel.setId(id);
                    return ResponseEntity.ok(carInventoryService.saveCarModel(carModel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        return carInventoryService.getCarModelById(id)
                .map(carModel -> {
                    carInventoryService.deleteCarModel(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
