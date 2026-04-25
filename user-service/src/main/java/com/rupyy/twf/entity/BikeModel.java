package com.rupyy.twf.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bike_models")
public class BikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "make_name")
    private String modelName;

}
