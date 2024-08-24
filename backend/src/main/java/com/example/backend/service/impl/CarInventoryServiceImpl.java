package com.example.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Car;
import com.example.backend.entity.CarModel;
import com.example.backend.exception.InvalidDataException;
import com.example.backend.repository.CarModelRepository;
import com.example.backend.repository.CarRepository;
import com.example.backend.request.CarRequest;
import com.example.backend.service.CarInventoryService;

@Service
public class CarInventoryServiceImpl implements CarInventoryService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll().stream().filter(Predicate.not(CarModel::isDeleted))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarModel> getCarModelById(Long id) {
        return carModelRepository.findById(id);
    }

    @Override
    public CarModel saveCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @Override
    public void deleteCarModel(Long id) {
        CarModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("CarModel not found"));
        carModel.setDeleted(true);
        carModelRepository.save(carModel);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll().stream().filter(Predicate.not(Car::isDeleted))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car saveCar(CarRequest carRequest) {
        CarModel carModel = carModelRepository.findById(carRequest.getModelId())
                .orElseThrow(() -> new InvalidDataException("CarModel not found"));

        Car car = new Car();
        car.setModel(carModel);
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setColor(carRequest.getColor());

        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, CarRequest carRequest) {
        if (!carRepository.existsById(id)) {
            throw new InvalidDataException("Car not found");
        }
        CarModel carModel = carModelRepository.findById(carRequest.getModelId())
                .orElseThrow(() -> new InvalidDataException("CarModel not found"));

        Car car = new Car();
        car.setId(id);
        car.setModel(carModel);
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setColor(carRequest.getColor());

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("Car not found"));
        car.setDeleted(true);
        carRepository.save(car);
    }

}
