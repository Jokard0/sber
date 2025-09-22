package com.jokard0.sber.mapper;

import com.jokard0.sber.model.ParkingRecord;
import com.jokard0.sber.model.ParkingRecordArchive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParkingRecordToParkingArchiveRecordMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "carNumber", source = "carNumber"),
            @Mapping(target = "carType", source = "carType"),
            @Mapping(target = "entryTime", source = "entryTime"),
            @Mapping(target = "exitTime", source = "exitTime")
    })
    ParkingRecordArchive toArchive(ParkingRecord record);

    @Mappings({
            @Mapping(target = "carNumber", source = "carNumber"),
            @Mapping(target = "carType", source = "carType"),
            @Mapping(target = "entryTime", source = "entryTime"),
            @Mapping(target = "exitTime", source = "exitTime")
    })
    ParkingRecord toRecord(ParkingRecordArchive archive);

    List<ParkingRecord> toRecord(List<ParkingRecordArchive> archives);
}
