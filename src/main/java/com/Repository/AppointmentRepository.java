package com.Repository;

import com.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Custom query methods can be added here if needed
    Appointment findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientIdAndDoctorId(Long patientId, Long doctorId);

    List<Appointment> findByPatientIdAndDoctorIdAndDate(Long patientId, Long doctorId, LocalDate date);
    List<Appointment> findByPatientIdAndDoctorIdAndDateAndTime(Long patientId, Long doctorId, LocalDate date, LocalTime time);

    List<Appointment> findByStatus(String status);

    List<Appointment> findByPatientIdAndStatus(Long patientId, String status);

    List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status);

    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);

    List<Appointment> findByHospitalId(Long hospitalId);

    List<Appointment> findByDepartmentId(Long departmentId);

    public Appointment getAppointmentById(Long id);



}


