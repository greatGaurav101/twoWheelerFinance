package com.rupyy.vehicle.controller;

import com.rupyy.vehicle.dto.APIResponseDTO1;
import com.rupyy.vehicle.dto.BikeMakeDTO;
import com.rupyy.vehicle.entity.BikeMake;
import com.rupyy.vehicle.service.MakeModelService;
import com.rupyy.vehicle.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mmv")
@CrossOrigin(origins = "http://localhost:4200")
public class MakeModelController {

    @Autowired
    private MakeModelService makeModelService;

    @Autowired
    private RedisService redisService;

    //http://localhost:8086/api/v1/mmv/save
    @PostMapping("/save")
    public ResponseEntity<APIResponseDTO1<BikeMakeDTO>> createMakeModel(@RequestBody BikeMakeDTO makeDTO) {

        BikeMakeDTO createdMakeModel = makeModelService.createMakeModel(makeDTO);

        APIResponseDTO1<BikeMakeDTO> response = new APIResponseDTO1<>();
        response.setMessage("MMV details created successfully");
        response.setData(createdMakeModel);
        response.setStatusCode(201);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    //http://localhost:8086/api/v1/mmv/get
    @GetMapping("/getbyid")
    public ResponseEntity<APIResponseDTO1<BikeMakeDTO>> getMMVdetails(@RequestParam Integer id) {


        // 1. Check Redis first
        BikeMakeDTO bikeMakeDTO = redisService.get(id, BikeMakeDTO.class);
        if (bikeMakeDTO != null) {
            APIResponseDTO1<BikeMakeDTO> getResponse = new APIResponseDTO1<>();
            getResponse.setStatusCode(200);
            getResponse.setMessage("MMV details fetched from redis cache");
            getResponse.setData(bikeMakeDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(getResponse);
        }
        // 2. Redis miss -> call DB/service
        BikeMakeDTO BikeMakeDTO = makeModelService.getMMVDetails(id);
        // 3. Store result in Redis
        if (BikeMakeDTO != null) {
            redisService.set(id, BikeMakeDTO, 120L);
        }

        // 4. Return response

        APIResponseDTO1<BikeMakeDTO> getResponse = new APIResponseDTO1<>();
        getResponse.setStatusCode(200);
        getResponse.setMessage("MMV details fetched successfully");
        getResponse.setData(BikeMakeDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getResponse);


    }
    //to get all makes
    @GetMapping("/getAll")
    public ResponseEntity<APIResponseDTO1<List<BikeMakeDTO>>> getAllMakes(){
        List<BikeMake> bikeMakeList = makeModelService.getAllMakes();

        List<BikeMakeDTO> bikeMakeDTOS = new ArrayList<>();



        for(BikeMake bm :bikeMakeList){
            BikeMakeDTO bikeMakeDTO = new BikeMakeDTO();
            BeanUtils.copyProperties(bm,bikeMakeDTO);
            bikeMakeDTOS.add(bikeMakeDTO);
        }



        APIResponseDTO1<List<BikeMakeDTO>> responseMakeDTO = new APIResponseDTO1<>();
        responseMakeDTO.setMessage("all makes fetched");
        responseMakeDTO.setStatusCode(200);
        responseMakeDTO.setData(bikeMakeDTOS);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMakeDTO);
    }


}
