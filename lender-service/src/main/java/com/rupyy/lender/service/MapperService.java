package com.rupyy.lender.service;

import com.rupyy.lender.dto.CitypincodemapperDto;
import com.rupyy.lender.entity.Citypincodemapper;
import com.rupyy.lender.repository.MapperRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperService {
    @Autowired
    private MapperRepository mapperRepository;

    public CitypincodemapperDto savePincodedealerDeatails(CitypincodemapperDto dto) {

        Citypincodemapper citypincodemapper = new Citypincodemapper();
        BeanUtils.copyProperties(dto,citypincodemapper);
        Citypincodemapper savedDetails = mapperRepository.save(citypincodemapper);

        List<Citypincodemapper> citypincodemapperList = mapperRepository.findAll();

        BeanUtils.copyProperties(citypincodemapperList,dto);
        return dto;
    }
}
