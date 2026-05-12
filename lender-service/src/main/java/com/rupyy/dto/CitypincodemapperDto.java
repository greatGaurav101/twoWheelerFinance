package com.rupyy.dto;

import com.rupyy.entity.Citypincodemapper;
import com.rupyy.entity.Dealer;
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