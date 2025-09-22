package com.jokard0.sber.controller;

import com.jokard0.sber.model.ParkingEntryRequest;
import com.jokard0.sber.model.ParkingExitRequest;
import com.jokard0.sber.model.ParkingRecord;
import com.jokard0.sber.model.ReportResponse;
import com.jokard0.sber.service.ParkingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/parking")
@AllArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/entry")
    public ResponseEntity<ParkingRecord> registerEntry(@Valid @RequestBody ParkingEntryRequest request) {
        ParkingRecord record = parkingService.registerEntry(request);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/exit")
    public ResponseEntity<ParkingRecord> registerExit(@Valid @RequestBody ParkingExitRequest request) {
        ParkingRecord record = parkingService.registerExit(request);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/report")
    public ResponseEntity<ReportResponse> getReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date) {

        ReportResponse report = parkingService.generateReport(start_date, end_date);
        return ResponseEntity.ok(report);
    }

}