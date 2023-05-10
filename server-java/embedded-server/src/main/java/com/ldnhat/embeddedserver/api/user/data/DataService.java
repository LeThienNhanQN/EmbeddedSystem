package com.ldnhat.embeddedserver.api.user.data;

import com.ldnhat.embeddedserver.api.user.data.dto.DataRequestDTO;
import com.ldnhat.embeddedserver.api.user.data.dto.DataResponseDTO;
import com.ldnhat.embeddedserver.common.entity.Data;
import com.ldnhat.embeddedserver.common.entity.DataCurrent;
import com.ldnhat.embeddedserver.common.repository.DataCurrentRepository;
import com.ldnhat.embeddedserver.common.repository.DataRepository;
import com.ldnhat.embeddedserver.common.service.MessageService;
import com.ldnhat.embeddedserver.enums.CommonEnum;
import com.ldnhat.embeddedserver.exception.NotFoundException;
import com.ldnhat.embeddedserver.utils.constants.APIConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;
    private final DataCurrentRepository dataCurrentRepository;

    private final MessageService messageService;

    public DataResponseDTO saveData(DataRequestDTO dataRequestDTO) {
        Data data = new Data();
        BeanUtils.copyProperties(dataRequestDTO, data);
        data.setCreatedAt(LocalDate.now());
        data.setCreatedAtTime(LocalDateTime.now());
        data.setSoilMoistureMeasure(CommonEnum.SoilMoistureMeasure.PERCENT);
        data = dataRepository.save(data);
        List<DataCurrent> dataCurrents = dataCurrentRepository.findAll();
        DataCurrent dataCurrent = dataCurrents.get(0);
        BeanUtils.copyProperties(dataRequestDTO, dataCurrent);
        dataCurrentRepository.save(dataCurrent);
        return new DataResponseDTO(data.getTemperature(),
                data.getHumidity(),
                data.getPosition(),
                data.getSoilMoisture(),
                data.getCreatedAt().toString(),
                data.getHumidityMeasure(),
                data.getTemperatureMeasure(),
                data.getSoilMoistureMeasure(),
                data.getId().toString()
        );
    }

    public DataCurrent saveDataCurrent(DataRequestDTO dataRequestDTO) {
        DataCurrent dataCurrent = new DataCurrent();
        BeanUtils.copyProperties(dataRequestDTO, dataCurrent);
        dataCurrent.setCreatedAt(LocalDate.now());
        return dataCurrentRepository.save(dataCurrent);
    }

    public void userDeleteData(String id) throws NotFoundException {
        Data data = dataRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundException.ERROR_DATA_NOT_FOUND, messageService.buildMessages(APIConstants.ERROR_DATA_NOT_FOUND))
        );
        dataRepository.deleteById(data.getId().toString());
    }

    public DataResponseDTO userGetDataCurrent() {
        List<DataCurrent> dataCurrents = dataCurrentRepository.findAll();
        return new DataResponseDTO(dataCurrents.get(0).getTemperature(),
                dataCurrents.get(0).getHumidity(),
                dataCurrents.get(0).getPosition(),
                dataCurrents.get(0).getSoilMoisture(),
                dataCurrents.get(0).getCreatedAt().toString(),
                dataCurrents.get(0).getHumidityMeasure(),
                dataCurrents.get(0).getTemperatureMeasure(),
                dataCurrents.get(0).getSoilMoistureMeasure(),
                dataCurrents.get(0).getId().toString()
        );
    }

    public List<DataResponseDTO> userGetAllData() {
        List<Data> data = dataRepository.findAll();
        return data.stream().map(b -> {
            DataResponseDTO dataResponseDTO = new DataResponseDTO();
            dataResponseDTO.setHumidity(b.getHumidity());
            dataResponseDTO.setTemperature(b.getTemperature());
            dataResponseDTO.setCreatedAt(b.getCreatedAt().toString());
            dataResponseDTO.setPosition(b.getPosition());
            dataResponseDTO.setSoilMoisture(b.getSoilMoisture());
            dataResponseDTO.setTemperatureMeasure(b.getTemperatureMeasure());
            dataResponseDTO.setHumidityMeasure(b.getHumidityMeasure());
            dataResponseDTO.setSoilMoistureMeasure(b.getSoilMoistureMeasure());
            dataResponseDTO.setId(b.getId().toString());
            return dataResponseDTO;
        }).collect(Collectors.toList());
    }
}
