package com.jokard0.sber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records_archive", indexes = {
        @Index(name = "idx_car_number_archive", columnList = "carNumber"),
        @Index(name = "idx_entry_time_archive", columnList = "entryTime"),
        @Index(name = "idx_exit_time_archive", columnList = "exitTime")
})
@Data
public class ParkingRecordArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String carNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    public ParkingRecordArchive() {}

    public ParkingRecordArchive(String carNumber, CarType carType, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.carNumber = carNumber;
        this.carType = carType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }
}
