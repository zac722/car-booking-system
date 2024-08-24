package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.entity.Car;
import com.example.backend.entity.CarModel;
import com.example.backend.request.CarRequest;

public interface CarInventoryService {

    public List<CarModel> getAllCarModels();

    public Optional<CarModel> getCarModelById(Long id);

    public CarModel saveCarModel(CarModel carModel);

    public void deleteCarModel(Long id);

    public List<Car> getAllCars();

    public Optional<Car> getCarById(Long id);

    public Car saveCar(CarRequest carRequest);

    public Car updateCar(Long id, CarRequest carRequest);

    public void deleteCar(Long id);
}
