package com.rupyy.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponseDTO1<T> {

    private String message;
    private int statusCode;
    private T data;




}
