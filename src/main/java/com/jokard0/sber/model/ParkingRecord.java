package com.jokard0.sber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records", indexes = {
        @Index(name = "idx_car_number", columnList = "carNumber"),
        @Index(name = "idx_entry_time", columnList = "entryTime"),
        @Index(name = "idx_exit_time", columnList = "exitTime")
})
@Data
public class ParkingRecord {

    @Id
    private String carNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    public ParkingRecord() {}

    public ParkingRecord(String carNumber, CarType carType, LocalDateTime entryTime) {
        this.carNumber = carNumber;
        this.carType = carType;
        this.entryTime = entryTime;
    }

}
