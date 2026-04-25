package com.rupyy.twf.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDTO {

        private String name;
        private String firstName;
        private String middleName;
        private String lastName;

        private String mobile;
        private LocalDate dob;
        private String panNumber;

        private String fatherName;
        private String email;

        private Integer gender;
        private Integer maritalStatus;
        private String education;

}
