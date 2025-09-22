package com.jokard0.sber.service;

import com.jokard0.sber.exception.CarAlreadyParkedException;
import com.jokard0.sber.exception.CarNotFoundException;
import com.jokard0.sber.mapper.ParkingRecordToParkingArchiveRecordMapper;
import com.jokard0.sber.model.*;
import com.jokard0.sber.repository.ParkingRecordArchiveRepository;
import com.jokard0.sber.repository.ParkingRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ParkingService {

    private final ParkingRecordRepository repository;
    private final ParkingRecordArchiveRepository archiveRepository;
    private final ParkingRecordToParkingArchiveRecordMapper parkingRecordToParkingArchiveRecordMapper;


    @Transactional
    public ParkingRecord registerEntry(ParkingEntryRequest request) {

        repository.findByCarNumberAndExitTimeIsNull(request.getCarNumber())
                .ifPresent(record -> {
                    throw new CarAlreadyParkedException("Car " + request.getCarNumber() + " is already parked");
                });

        ParkingRecord record = new ParkingRecord(
                request.getCarNumber(),
                request.getCarType(),
                LocalDateTime.now()
        );

        return repository.save(record);
    }

    @Transactional
    public ParkingRecord registerExit(ParkingExitRequest request) {

        ParkingRecord record = repository.findByCarNumberAndExitTimeIsNull(request.getCarNumber())
                .orElseThrow(() -> new CarNotFoundException("Car " + request.getCarNumber() + " not found or already exited"));
        record.setExitTime(LocalDateTime.now());

        ParkingRecordArchive archivedRecord = parkingRecordToParkingArchiveRecordMapper.toArchive(record);
        archiveRepository.save(archivedRecord);
        repository.delete(record);

        return record;
    }

    public ReportResponse generateReport(LocalDateTime startDate, LocalDateTime endDate) {
        long totalEntries = repository.countEntriesBetween(startDate, endDate)
                + archiveRepository.countEntriesBetween(startDate, endDate);

        long totalExits = repository.countExitsBetween(startDate, endDate)
                + archiveRepository.countExitsBetween(startDate, endDate);

        List<ParkingRecord> completedSessions = parkingRecordToParkingArchiveRecordMapper
                .toRecord(
                        archiveRepository.findCompletedSessionsBetween(startDate, endDate)
                );

        double averageParkingTime = completedSessions.stream()
                .mapToLong(record -> Duration.between(record.getEntryTime(), record.getExitTime()).toMinutes())
                .average()
                .orElse(0.0);

        return new ReportResponse(totalEntries, totalExits, averageParkingTime);
    }

}