package com.rupyy.vehicle.dto;

import com.rupyy.vehicle.entity.BikeModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BikeMakeDTO {

    private int makeId;
    private String makeName;
    private int popularity;
    private String makeImage;
    private List<BikeModel> bikeModels;
}
