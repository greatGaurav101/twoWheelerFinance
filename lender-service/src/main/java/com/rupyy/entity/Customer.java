package com.rupyy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "leads_id")
    private UUID leadsId;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    /** Encrypted mobile number */
    @Column(name = "mobile")
    private String mobile;

    @Column(name = "dob")
    private LocalDate dob;

    /** Encrypted PAN number */
    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "father_name")
    private String fatherName;

    /** Encrypted email */
    @Column(name = "email")
    private String email;

    /** 1 = Male, 2 = Female */
    @Column(name = "gender")
    private Integer gender;

    /** 0 = Single, 1 = Married */
    @Column(name = "marital_status")
    private Integer maritalStatus;

    @Column(name = "education")
    private String education;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    //private List<Lead> leads;

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
