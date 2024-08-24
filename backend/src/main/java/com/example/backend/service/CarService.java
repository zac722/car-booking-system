package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.backend.entity.Booking;
import com.example.backend.entity.CarModel;

public interface CarService {

    public List<CarModel> getAllCarModelsWithCars();

    public List<LocalDate> getAvailableDates(Long carModelId, LocalDate startDate, LocalDate endDate);

    public Map<Long, List<LocalDate>> getAllCarModelsAvailableDates(LocalDate startDate, LocalDate endDate);

    public Booking bookCar(Long userId, Long carModelId, LocalDate startDate, LocalDate endDate);

    public void comfirmBooking(Long bookingId);

    public void cancelBooking(Long bookingId);

    public Booking getBooking(Long bookingId);

    public List<Booking> getUserBookings(Long userId);
}