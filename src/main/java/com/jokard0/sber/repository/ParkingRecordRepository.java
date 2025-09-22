package com.jokard0.sber.repository;

import com.jokard0.sber.model.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    Optional<ParkingRecord> findByCarNumberAndExitTimeIsNull(String carNumber);

    @Query("SELECT COUNT(p) FROM ParkingRecord p WHERE p.entryTime BETWEEN :start AND :end")
    long countEntriesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM ParkingRecord p WHERE p.exitTime BETWEEN :start AND :end")
    long countExitsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    @Query("SELECT COUNT(p) FROM ParkingRecord p WHERE p.exitTime IS NULL")
    long countCurrentlyParkedCars();
}