package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    
    List<CarModel> findAllByIdIn(List<Long> ids);
    
}
