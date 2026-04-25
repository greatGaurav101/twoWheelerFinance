package com.rupyy.twf.dto;

import java.util.UUID;

public class APIResponseDTO<T> {

    private UUID id;
    private UUID leadsId;
    private String message;
    private int statusCode;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLeadsId(UUID leadsId) {
        this.leadsId = leadsId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UUID getId() {
        return id;
    }

    public UUID getLeadsId() {
        return leadsId;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }


}
