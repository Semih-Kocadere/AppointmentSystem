package com.Services;

import com.Entity.Appointment;

import java.util.List;


public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    void saveAppointment(Appointment appointment);
    void deleteAppointment(Long id);
    Appointment getAppointmentByUserId(Long userId);
    // Other methods as needed
}

