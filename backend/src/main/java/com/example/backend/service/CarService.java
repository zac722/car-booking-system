package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;

import com.example.backend.entity.Booking;
import com.example.backend.entity.CarModel;

public interface CarService {

    public List<CarModel> getAllCarModelsWithCars();

    public List<LocalDate> getUnavailableDates(Long carModelId, LocalDate startDate, LocalDate endDate);

    public Booking bookCar(Long userId, Long carModelId, LocalDate startDate, LocalDate endDate);

    public void comfirmBooking(Long bookingId);

    public void cancelBooking(Long bookingId);

    public List<Booking> getUserBookings(Long userId);
}