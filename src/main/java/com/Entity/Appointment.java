package com.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    // Constructors, getters, and setters


    public Appointment(long id, LocalDate date, LocalTime time, String status, String reason, LocalDateTime createdAt, LocalDateTime updatedAt, Hospital hospital, Department department, Doctor doctor) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.status = status;
    this.reason = reason;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.hospital = hospital;
    this.department = department;
    this.doctor= doctor;
    }

    public LocalDate getAppointmentDate() {
        return date;
    }

    public LocalTime getAppointmentTime() {
        return time;
    }
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.date = appointmentDate;
    }



//    public  void appointmentInfo(){
//        System.out.println("You have an appointment in " + department + " at " + appointmentDate + " from " +
//                doctor + appointmentDate + ".");
//    }
}
