package com.rupyy1.service;

import com.rupyy1.dto.BikeMakeDTO;
import com.rupyy1.entity.BikeMake;
import com.rupyy1.entity.BikeModel;
import com.rupyy1.repository.BikeMakeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeModelService {

    @Autowired
    private BikeMakeRepository makeRepository;

    public BikeMakeDTO createMakeModel(BikeMakeDTO makeDTO){

        BikeMake bikemake = new BikeMake();
        BeanUtils.copyProperties(makeDTO,bikemake);

        if (makeDTO.getBikeModels() != null){

            for(BikeModel bikeModel : makeDTO.getBikeModels()){

                bikeModel.setMake1(bikemake);
            }
        }

        makeRepository.save(bikemake);

        BikeMakeDTO makeDTO1 = new BikeMakeDTO();
        BeanUtils.copyProperties(bikemake,makeDTO1);

        return makeDTO1;
    }

    public BikeMakeDTO getMMVDetails(Integer id) {

        Optional<BikeMake> details = makeRepository.findById(id);
        BikeMake bikeMake = details.get();

        BikeMakeDTO bikeMakeDTO = new BikeMakeDTO();
        BeanUtils.copyProperties(bikeMake,bikeMakeDTO);


        return bikeMakeDTO;

    }
}
