package com.jokard0.sber.repository;

import com.jokard0.sber.model.ParkingRecordArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingRecordArchiveRepository extends JpaRepository<ParkingRecordArchive, Long> {


    @Query("SELECT COUNT(p) FROM ParkingRecordArchive p WHERE p.entryTime BETWEEN :start AND :end")
    long countEntriesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM ParkingRecordArchive p WHERE p.exitTime BETWEEN :start AND :end")
    long countExitsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT p FROM ParkingRecordArchive p WHERE p.exitTime IS NOT NULL " +
            "AND p.exitTime BETWEEN :start AND :end")
    List<ParkingRecordArchive> findCompletedSessionsBetween(@Param("start") LocalDateTime start,
                                                     @Param("end") LocalDateTime end);

}
