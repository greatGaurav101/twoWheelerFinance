package com.rupyy.lender.controller;

import com.rupyy.lender.dto.APIResponseDTO;
import com.rupyy.lender.dto.CitypincodemapperDto;
import com.rupyy.lender.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mapper")
public class mapperController {

    @Autowired
    private MapperService mapperService;

    //http://localhost:8087/api/v1/mapper/savedealers
    @PostMapping("/savedealers")
    ResponseEntity<APIResponseDTO<CitypincodemapperDto>> savePincodedealerDeatails(@RequestBody CitypincodemapperDto dto){
        CitypincodemapperDto citypincodemapperDto = mapperService.savePincodedealerDeatails(dto);

        APIResponseDTO<CitypincodemapperDto> responseDTO = new APIResponseDTO<>();
        responseDTO.setStatusCode(201);
        responseDTO.setMessage("dealer details added");
        responseDTO.setData(citypincodemapperDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);

    }

}
