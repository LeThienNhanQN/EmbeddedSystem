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
public class DataRequestDTO {
    private String temperature;
    private CommonEnum.TemperatureMeasure temperatureMeasure;
    private String humidity;
    private CommonEnum.HumidityMeasure humidityMeasure;
    private String position;
    private String soilMoisture;
    private CommonEnum.SoilMoistureMeasure soilMoistureMeasure;
}
