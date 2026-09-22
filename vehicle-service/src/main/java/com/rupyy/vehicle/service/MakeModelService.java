package com.rupyy.vehicle.service;

import com.rupyy.vehicle.dto.BikeMakeDTO;
import com.rupyy.vehicle.entity.BikeMake;
import com.rupyy.vehicle.entity.BikeModel;
import com.rupyy.vehicle.repository.BikeMakeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeModelService {

    @Autowired
    private BikeMakeRepository makeRepository;

    @Autowired
    private RedisService redisService;

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

   // @Cacheable(value = "mmvDetails", key = "#id")
    public BikeMakeDTO getMMVDetails(Integer id) {


            Optional<BikeMake> details = makeRepository.findById(id);
            BikeMake bikeMake = details.get();

            BikeMakeDTO bikeMakeDTO = new BikeMakeDTO();
            BeanUtils.copyProperties(bikeMake,bikeMakeDTO);

            return bikeMakeDTO;
    }
}