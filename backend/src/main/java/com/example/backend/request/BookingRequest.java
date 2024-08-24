package com.example.backend.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingRequest {

    private Long userId;
    private Long carModelId;
    private LocalDate startDate;
    private LocalDate endDate;

}
