package com.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "experience")
    private int experience;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Doctor(String name) {
        this.name = name;
    }

    public Doctor(String name, String specialization, String phone, String email, String gender, int age, String qualification, int experience, Hospital hospital, Department department) {
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.gender = gender;
        this.age = age;
        this.qualification = qualification;
        this.specialization = specialization;
        this.hospital = hospital;
        this.department = department;
    }
}
