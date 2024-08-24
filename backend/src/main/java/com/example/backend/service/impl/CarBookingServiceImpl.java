package com.example.backend.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Booking;
import com.example.backend.entity.Car;
import com.example.backend.entity.CarModel;
import com.example.backend.exception.InvalidDataException;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.CarModelRepository;
import com.example.backend.repository.CarRepository;
import com.example.backend.service.CarBookingService;

@Service
public class CarBookingServiceImpl implements CarBookingService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<CarModel> getAllCarModelsWithCars() {
        List<Car> allCars = carRepository.findAll();
        List<Long> carModelIds = allCars.stream()
                .map(car -> car.getModel().getId())
                .distinct()
                .collect(Collectors.toList());
        return carModelRepository.findAllByIdIn(carModelIds).stream().filter(Predicate.not(CarModel::isDeleted))
                .collect(Collectors.toList());
    }

    @Override
    public Booking bookCar(Long userId, Long carModelId, LocalDate startDate, LocalDate endDate) {
        CarModel carModel = carModelRepository.findById(carModelId)
                .orElseThrow(() -> new InvalidDataException("Car model not found"));

        // Find available cars under this car model
        Car availableCar = findAvailableCar(carModel, startDate, endDate);
        if (availableCar == null) {
            throw new InvalidDataException("No available car for the selected dates");
        }

        double totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalPrice = totalDays * carModel.getDailyRate();

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setCar(availableCar);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setTotalPrice(totalPrice);
        booking.setStatus("Pending");

        return bookingRepository.save(booking);
    }

    private Car findAvailableCar(CarModel carModel, LocalDate startDate, LocalDate endDate) {
        List<Car> cars = carRepository.findByModel(carModel);
        for (Car car : cars) {
            if (isCarAvailable(car, startDate, endDate)) {
                return car;
            }
        }
        return null;
    }

    private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
        List<Booking> bookings = bookingRepository.findByCar(car);
        for (Booking booking : bookings) {
            if (!booking.getStatus().equals("Cancelled") &&
                    !(booking.getEndDate().isBefore(startDate) || booking.getStartDate().isAfter(endDate))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<LocalDate> getAvailableDates(Long carModelId, LocalDate startDate, LocalDate endDate) {
        CarModel carModel = carModelRepository.findById(carModelId)
                .orElseThrow(() -> new InvalidDataException("Car model not found"));

        List<Car> cars = carRepository.findByModel(carModel);
        Map<LocalDate, Integer> dateOccupancy = new HashMap<>();

        // Get all bookings within the date range
        List<Booking> allBookings = bookingRepository
                .findByCarInAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusNot(
                        cars, endDate, startDate, "Cancelled");

        // Calculate the number of occupied cars per day
        for (Booking booking : allBookings) {
            LocalDate bookingStart = booking.getStartDate().isBefore(startDate) ? startDate : booking.getStartDate();
            LocalDate bookingEnd = booking.getEndDate().isAfter(endDate) ? endDate : booking.getEndDate();

            LocalDate date = bookingStart;
            while (!date.isAfter(bookingEnd)) {
                dateOccupancy.merge(date, 1, Integer::sum);
                date = date.plusDays(1);
            }
        }

        // If the number of occupied cars on a day is less than the total number of
        // cars,
        // the day is available
        List<LocalDate> availableDates = new ArrayList<>();
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (dateOccupancy.getOrDefault(date, 0) < cars.size()) {
                availableDates.add(date);
            }
            date = date.plusDays(1);
        }

        return availableDates;
    }

    @Override
    public Map<Long, List<LocalDate>> getAllCarModelsAvailableDates(LocalDate startDate, LocalDate endDate) {
        List<CarModel> carModels = carModelRepository.findAll();
        Map<Long, List<LocalDate>> result = new HashMap<>();

        for (CarModel carModel : carModels) {
            List<LocalDate> availableDates = getAvailableDates(carModel.getId(), startDate, endDate);
            result.put(carModel.getId(), availableDates);
        }

        return result;
    }

    @Override
    public void comfirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidDataException("Booking not found"));
        booking.setStatus("Confirmed");
        bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidDataException("Booking not found"));
        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
    }

    @Override
    public Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidDataException("Booking not found"));
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

}