package com.ldnhat.embeddedserver.api.user.data;

import com.ldnhat.embeddedserver.api.user.data.dto.DataRequestDTO;
import com.ldnhat.embeddedserver.api.user.data.dto.DataResponseDTO;
import com.ldnhat.embeddedserver.common.entity.DataCurrent;
import com.ldnhat.embeddedserver.common.response.APIResponse;
import com.ldnhat.embeddedserver.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/data")
@RequiredArgsConstructor
public class DataController {
    private static final String USER_SAVE_DATA_ENDPOINT = "";
    private static final String USER_SAVE_DATA_CURRENT_ENDPOINT = "/current";
    private static final String USER_DELETE_DATA_ENDPOINT = "/{id}";
    private static final String USER_GET_DATA_CURRENT_ENDPOINT = "/current";
    private static final String USER_GET_ALL_DATA_ENDPOINT = "";

    private final DataService dataService;

    @ApiOperation("User save data")
    @PostMapping(USER_SAVE_DATA_ENDPOINT)
    public APIResponse<DataResponseDTO> userSaveData(@RequestBody DataRequestDTO dataRequestDTO) {
        return APIResponse.okStatus(dataService.saveData(dataRequestDTO));
    }

    @ApiOperation("User save data current")
    @PostMapping(USER_SAVE_DATA_CURRENT_ENDPOINT)
    public APIResponse<DataCurrent> userSaveDataCurrent(@RequestBody DataRequestDTO dataRequestDTO) {
        return APIResponse.okStatus(dataService.saveDataCurrent(dataRequestDTO));
    }

    @ApiOperation("User delete data")
    @PostMapping(USER_DELETE_DATA_ENDPOINT)
    public APIResponse<Void> userDeleteData(@PathVariable String id) throws NotFoundException {
        dataService.userDeleteData(id);
        return APIResponse.okStatus();
    }

    @ApiOperation("User get data current")
    @GetMapping(USER_GET_DATA_CURRENT_ENDPOINT)
    public APIResponse<DataResponseDTO> userGetDataCurrent() {
        return APIResponse.okStatus(dataService.userGetDataCurrent());
    }

    @ApiOperation("User get all data")
    @GetMapping(USER_GET_ALL_DATA_ENDPOINT)
    public APIResponse<List<DataResponseDTO>> userGetAllData() {
        return APIResponse.okStatus(dataService.userGetAllData());
    }
}
