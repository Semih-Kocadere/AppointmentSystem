package com.Services;

import com.Entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    void saveDoctor(Doctor doctor);
    void deleteDoctor(Long id);

    Doctor getDoctorByName(String name);

    // Other methods as needed
}


