package com.rupyy.lender.service;

import com.rupyy.lender.dto.CitypincodemapperDto;
import com.rupyy.lender.entity.Citypincodemapper;
import com.rupyy.lender.entity.Dealer;
import com.rupyy.lender.repository.DealerRepository;
import com.rupyy.lender.repository.MapperRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapperService {
    @Autowired
    private MapperRepository mapperRepository;

    @Autowired
    private DealerRepository dealerRepository;

    public CitypincodemapperDto savePincodedealerDeatails(CitypincodemapperDto dto) {

        Citypincodemapper citypincodemapper = new Citypincodemapper();
        BeanUtils.copyProperties(dto,citypincodemapper);

        if(dto.getDealers() != null){

                citypincodemapper.setDealers(dto.getDealers());

        }

        Citypincodemapper savedDetails = mapperRepository.save(citypincodemapper);

        BeanUtils.copyProperties(savedDetails,dto);
        return dto;
    }

    public void getDetails() {
        List<Citypincodemapper> list = mapperRepository.findAll();
        for(Citypincodemapper cpm : list){
            System.out.println(cpm.getCityId());
            System.out.println(cpm.getCityName());
            System.out.println(cpm.getPincode());
            cpm.getDealers().stream().map(x->x.getDealerName()).forEach(System.out::println);
            cpm.getDealers().stream().map(x->x.getId()).forEach(System.out::println);
        }

        /*Optional<Dealer> dealer = dealerRepository.findById(3);
        Dealer dealer1 = dealer.get();

        System.out.println(dealer1.getDealerName());
        System.out.println(dealer1.getDealerAddress());*/


    }
}
