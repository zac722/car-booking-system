package com.example.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Booking;
import com.example.backend.entity.Car;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByCar(Car car);

    List<Booking> findByCarInAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusNot(List<Car> cars,
            LocalDate endDate, LocalDate startDate, String status);
}
