package com.jokard0.sber.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingEntryRequest {

    @NotNull(message = "Car number is required")
    private String carNumber;

    @NotNull(message = "Car type is required")
    private CarType carType;

}