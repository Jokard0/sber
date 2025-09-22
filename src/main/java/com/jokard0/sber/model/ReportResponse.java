package com.jokard0.sber.model;

import lombok.Data;

@Data
public class ReportResponse {
    private long totalEntries;
    private long totalExits;
    private double averageParkingTimeMinutes;

    public ReportResponse(long totalEntries, long totalExits, double averageParkingTimeMinutes) {
        this.totalEntries = totalEntries;
        this.totalExits = totalExits;
        this.averageParkingTimeMinutes = averageParkingTimeMinutes;
    }
}
