package com.Controllers;

import com.DTO.AppointmentDetailsDTO;
import com.Entity.*;
import com.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailsDTO> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);

        AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
        dto.setAppointmentId(appointment.getId());
        LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.getAppointmentDate(), appointment.getAppointmentTime());
        dto.setAppointmentDate(appointmentDateTime.toLocalDate());
        dto.setAppointmentTime(appointmentDateTime.toLocalTime());
        dto.setDepartmentName(appointment.getDepartment().getName());
        dto.setHospitalName(appointment.getHospital().getName());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setPatientName(appointment.getPatient().getName());
        dto.setStatus(appointment.getStatus());
        dto.setReason(appointment.getReason());
        dto.setCreatedAt(appointment.getCreatedAt());
        dto.setUpdatedAt(appointment.getUpdatedAt());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@ModelAttribute Appointment appointment,
                                                    @RequestParam String appointmentDate,
                                                    @RequestParam String appointmentTime,
                                                    @RequestParam String doctorName,
                                                    @RequestParam String patientName,
                                                    @RequestParam String hospitalName,
                                                    @RequestParam String departmentName) {
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        if (appointmentDate == null || appointmentDate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment date cannot be null");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(appointmentDate, dateFormatter);
        appointment.setAppointmentDate(date);

        if (appointmentTime != null) {
            LocalTime time = LocalTime.parse(appointmentTime);
            appointment.setTime(time);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment time cannot be null");
        }

        Doctor doctor = doctorService.getDoctorByName(doctorName);
        User patient = userService.getUserByName(patientName);
        Hospital hospital = hospitalService.getHospitalByName(hospitalName);
        Department department = departmentService.getDepartmentByNameAndHospitalName(departmentName, hospitalName);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setHospital(hospital);
        appointment.setDepartment(department);

        appointment.setStatus("Scheduled");
        appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateAppointmentByUserId(@PathVariable Long userId,
                                                            @RequestParam(required = false) String appointmentDate,
                                                            @RequestParam(required = false) String appointmentTime,
                                                            @RequestParam(required = false) String doctorName,
                                                            @RequestParam(required = false) String patientName,
                                                            @RequestParam(required = false) String hospitalName,
                                                            @RequestParam(required = false) String departmentName,
                                                            @RequestParam(required = false) String reason) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with id: " + userId);
        }

        Appointment appointment = appointmentService.getAppointmentByUserId(userId);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment not found for user id: " + userId);
        }

        if (appointmentDate != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(appointmentDate, dateFormatter);
            appointment.setAppointmentDate(date);
        }

        if (appointmentTime != null) {
            LocalTime time = LocalTime.parse(appointmentTime);
            appointment.setTime(time);
        }

        if (doctorName != null) {
            Doctor doctor = doctorService.getDoctorByName(doctorName);
            appointment.setDoctor(doctor);
        }

        if (patientName != null) {
            user = userService.getUserByName(patientName);
            appointment.setPatient(user);
        }

        if (hospitalName != null) {
            Hospital hospital = hospitalService.getHospitalByName(hospitalName);
            appointment.setHospital(hospital);
        }

        if (departmentName != null) {
            Department department = departmentService.getDepartmentByNameAndHospitalName(departmentName, hospitalName);
            appointment.setDepartment(department);
        }

        if (reason != null) {
            appointment.setReason(reason);
        }

        appointment.setUpdatedAt(LocalDateTime.now());
        appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found with id: " + id);
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }

    // Other endpoints as needed
}