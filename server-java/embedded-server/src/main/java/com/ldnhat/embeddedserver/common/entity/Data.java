package com.ldnhat.embeddedserver.common.entity;

import com.ldnhat.embeddedserver.enums.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
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
@Table(value = "data")
public class Data {
    @PrimaryKey
    @CassandraType(type = Name.UUID)
    private UUID id = UUID.randomUUID();

    @Column("temperature")
    @CassandraType(type = Name.TEXT)
    private String temperature;

    @Column("temperature_measure")
    @CassandraType(type = Name.TEXT)
    private CommonEnum.TemperatureMeasure temperatureMeasure;

    @Column("humidity")
    @CassandraType(type = Name.TEXT)
    private String humidity;

    @Column("humidity_measure")
    @CassandraType(type = Name.TEXT)
    private CommonEnum.HumidityMeasure humidityMeasure;

    @Column("soil_moisture")
    @CassandraType(type = Name.TEXT)
    private String soilMoisture;

    @Column("soil_moisture_measure")
    @CassandraType(type = Name.TEXT)
    private CommonEnum.SoilMoistureMeasure soilMoistureMeasure;

    @Column("position")
    @CassandraType(type = Name.TEXT)
    private String position;

    @Column("created_at")
    @CassandraType(type = Name.DATE)
    private LocalDate createdAt;

    @Column("created_at_time")
    @CassandraType(type = Name.TIMESTAMP)
    private LocalDateTime createdAtTime;
}
