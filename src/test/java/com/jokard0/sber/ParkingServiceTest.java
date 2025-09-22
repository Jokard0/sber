package com.jokard0.sber;

import com.jokard0.sber.exception.CarAlreadyParkedException;
import com.jokard0.sber.exception.CarNotFoundException;
import com.jokard0.sber.model.CarType;
import com.jokard0.sber.model.ParkingEntryRequest;
import com.jokard0.sber.model.ParkingExitRequest;
import com.jokard0.sber.model.ParkingRecord;
import com.jokard0.sber.repository.ParkingRecordRepository;
import com.jokard0.sber.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @Mock
    private ParkingRecordRepository repository;

    @InjectMocks
    private ParkingService parkingService;



    @Test
    void registerEntry_CarAlreadyParked_ThrowsException() {
        ParkingEntryRequest request = new ParkingEntryRequest();
        request.setCarNumber("AA000A");
        request.setCarType(CarType.CAR);

        ParkingRecord existingRecord = new ParkingRecord();
        existingRecord.setCarNumber("AA000A");

        when(repository.findByCarNumberAndExitTimeIsNull("AA000A"))
                .thenReturn(Optional.of(existingRecord));

        assertThrows(CarAlreadyParkedException.class, () -> {
            parkingService.registerEntry(request);
        });
    }



    @Test
    void registerExit_CarNotFound_ThrowsException() {
        ParkingExitRequest request = new ParkingExitRequest();
        request.setCarNumber("AA000A");

        when(repository.findByCarNumberAndExitTimeIsNull("AA000A"))
                .thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> {
            parkingService.registerExit(request);
        });
    }
}