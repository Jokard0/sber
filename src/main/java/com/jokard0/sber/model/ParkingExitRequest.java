package com.jokard0.sber.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingExitRequest {

    @NotNull(message = "Car number is required")
    private String carNumber;

}