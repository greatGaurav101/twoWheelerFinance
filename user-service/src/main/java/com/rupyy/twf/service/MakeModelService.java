package com.rupyy.twf.service;

import com.rupyy.twf.dto.BikeMakeDTO;
import com.rupyy.twf.entity.BikeMake;
import com.rupyy.twf.repository.BikeMakeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeModelService {

    @Autowired
    private BikeMakeRepository makeRepository;

    public BikeMakeDTO createMakeModel(BikeMakeDTO makeDTO){

        BikeMake bikemake = new BikeMake();
        BeanUtils.copyProperties(makeDTO,bikemake);
        makeRepository.save(bikemake);

        BikeMakeDTO makeDTO1 = new BikeMakeDTO();
        BeanUtils.copyProperties(bikemake,makeDTO1);

        return makeDTO1;
    }
}
