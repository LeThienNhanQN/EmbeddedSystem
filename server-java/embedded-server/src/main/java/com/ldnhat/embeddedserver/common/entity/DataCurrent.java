package com.ldnhat.embeddedserver.common.entity;

import com.ldnhat.embeddedserver.enums.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "data_current")
public class DataCurrent {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();

    @Column("temperature")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String temperature;

    @Column("temperature_measure")
    @CassandraType(type = CassandraType.Name.TEXT)
    private CommonEnum.TemperatureMeasure temperatureMeasure;

    @Column("humidity")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String humidity;

    @Column("humidity_measure")
    @CassandraType(type = CassandraType.Name.TEXT)
    private CommonEnum.HumidityMeasure humidityMeasure;

    @Column("created_at")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate createdAt;

    @Column("position")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String position;

    @Column("soil_moisture")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String soilMoisture;

    @Column("soil_moisture_measure")
    @CassandraType(type = CassandraType.Name.TEXT)
    private CommonEnum.SoilMoistureMeasure soilMoistureMeasure;
}
