package com.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentDetailsDTO {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String departmentName;
    private String hospitalName;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private String status;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}