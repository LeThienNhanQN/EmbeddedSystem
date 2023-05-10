package com.ldnhat.embeddedserver.api.user.data.dto;

import com.ldnhat.embeddedserver.enums.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseDTO {
    private String temperature;
    private String humidity;
    private String position;
    private String soilMoisture;
    private String createdAt;
    private CommonEnum.HumidityMeasure humidityMeasure;
    private CommonEnum.TemperatureMeasure temperatureMeasure;
    private CommonEnum.SoilMoistureMeasure soilMoistureMeasure;
    private String id;
}
