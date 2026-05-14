package com.rupyy.lender.dto;

import com.rupyy.lender.entity.Citypincodemapper;
import com.rupyy.lender.entity.Dealer;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Citypincodemapper}
 */
@Value
public class CitypincodemapperDto implements Serializable {
    Integer cityId;
    String cityName;
    String pincode;
    List<Dealer> dealers;

}