package com.rupyy1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bike_makes")
public class BikeMake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "make_id",unique = true,nullable = true)
    private int makeId;

    private String makeName;
    private int popularity;
    private String makeImage;

    @OneToMany(mappedBy = "make1" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<BikeModel> bikeModels;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
