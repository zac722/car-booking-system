package com.example.backend.request;

import lombok.Data;

@Data
public class CarRequest {

    private Long modelId;
    private String licensePlate;
    private String color;

}
