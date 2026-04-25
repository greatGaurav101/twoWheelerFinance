package com.rupyy.twf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BikeMakeDTO {

    private int makeId;
    private String makeName;
    private int popularity;
    private String makeImage;
}
