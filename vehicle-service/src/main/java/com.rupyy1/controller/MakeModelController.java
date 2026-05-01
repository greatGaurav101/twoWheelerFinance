package com.rupyy1.controller;

import com.rupyy1.dto.APIResponseDTO1;
import com.rupyy1.dto.BikeMakeDTO;
import com.rupyy1.entity.BikeMake;
import com.rupyy1.service.MakeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mmv")
public class MakeModelController {

    @Autowired
    private MakeModelService makeModelService;

    //http://localhost:8086/api/v1/mmv/save
    @PostMapping("/save")
    public ResponseEntity<APIResponseDTO1<BikeMakeDTO>> createMakeModel(@RequestBody BikeMakeDTO makeDTO){

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
    @GetMapping("/get")
    public ResponseEntity<APIResponseDTO1<BikeMakeDTO>> getMMVdetails(@RequestParam Integer id){

        BikeMakeDTO BikeMakeDTO = makeModelService.getMMVDetails(id);

        APIResponseDTO1<BikeMakeDTO> getResponse = new APIResponseDTO1<>();
        getResponse.setStatusCode(200);
        getResponse.setMessage("MMV details fetched successfully");
        getResponse.setData(BikeMakeDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getResponse);

    }




}
