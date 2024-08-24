package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Car;
import com.example.backend.entity.CarModel;

public interface CarRepository extends JpaRepository<Car, Long> {
    
     List<Car> findByModel(CarModel carModel);
     
}
