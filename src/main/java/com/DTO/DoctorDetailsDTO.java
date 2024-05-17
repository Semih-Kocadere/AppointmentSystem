package com.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDetailsDTO {
    private Long id;
    private String name;
    private String specialization;
    private String departmentName;
    private String hospitalName;
    private String phone;
    private String email;
    private String gender;
    private int age;
    private String qualification;
    private int experience;
}
