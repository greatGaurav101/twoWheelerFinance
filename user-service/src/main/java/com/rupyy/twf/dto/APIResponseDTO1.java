package com.rupyy.twf.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class APIResponseDTO1<T> {

    private String message;
    private int statusCode;
    private T data;




}
