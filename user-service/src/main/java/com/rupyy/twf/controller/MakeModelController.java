package com.rupyy.twf.controller;

import com.rupyy.twf.dto.APIResponseDTO1;
import com.rupyy.twf.dto.BikeMakeDTO;
import com.rupyy.twf.service.MakeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mmv")
public class MakeModelController {

    @Autowired
    private MakeModelService makeModelService;

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
}
