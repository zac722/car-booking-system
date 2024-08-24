package com.example.backend.controller;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/car-models/{carModelId}/unavailable-dates")
    public ResponseEntity<List<LocalDate>> getUnavailableDates(
            @PathVariable Long carModelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LocalDate> unavailableDates = carService.getUnavailableDates(carModelId, startDate, endDate);
        return ResponseEntity.ok(unavailableDates);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> bookCar(@RequestBody BookingRequest request) {
        Booking booking = carService.bookCar(request.getUserId(), request.getCarModelId(),
                request.getStartDate(), request.getEndDate());
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
