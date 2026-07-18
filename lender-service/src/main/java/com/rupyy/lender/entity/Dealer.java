package com.rupyy.lender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dealer", schema = "lender-db")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dealer_name")
    private String dealerName;

    @Column(name = "dealer_mobile")
    private String dealerMobile;

    @Column(name = "dealer_address")
    private String dealerAddress;

    @Column(name = "dealer_email")
    private String dealerEmail;

    @Column(name = "dealer_make", length = 45)
    private String dealerMake;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "mapper_pincode", referencedColumnName = "pincode")
    //private Citypincodemapper mapperPincode;


}