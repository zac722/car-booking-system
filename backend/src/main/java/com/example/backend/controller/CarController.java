package com.example.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Booking;
import com.example.backend.entity.CarModel;
import com.example.backend.request.BookingRequest;
import com.example.backend.service.CarService;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        List<CarModel> carModels = carService.getAllCarModelsWithCars();
        return ResponseEntity.ok(carModels);
    }

    @GetMapping("/car-models/{carModelId}/available-dates")
    public ResponseEntity<List<LocalDate>> getAvailableDates(
            @PathVariable Long carModelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LocalDate> availableDates = carService.getAvailableDates(carModelId, startDate, endDate);
        return ResponseEntity.ok(availableDates);
    }

    @GetMapping("/car-models/available-dates")
    public ResponseEntity<Map<Long, List<LocalDate>>> getAllCarModelsAvailableDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<Long, List<LocalDate>> availableDates = carService.getAllCarModelsAvailableDates(startDate, endDate);
        return ResponseEntity.ok(availableDates);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> bookCar(@RequestBody BookingRequest request) {
        Booking booking = carService.bookCar(request.getUserId(), request.getCarModelId(),
                request.getStartDate(), request.getEndDate());
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long bookingId) {
        Booking booking = carService.getBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        carService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        List<Booking> bookings = carService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
}
