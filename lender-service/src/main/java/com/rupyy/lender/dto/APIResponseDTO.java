package com.rupyy.lender.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponseDTO<T> {

    private String message;
    private int statusCode;
    private T data;




}
