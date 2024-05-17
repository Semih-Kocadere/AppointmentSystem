package com.Services;

import com.Entity.Hospital;

import java.util.List;


public interface HospitalService {
    List<Hospital> getAllHospitals();
    Hospital getHospitalById(Long id);
    void saveHospital(Hospital hospital);
    void deleteHospital(Long id);

    boolean existsById(Long id);

    Hospital getHospitalByName(String name);

    // Other methods as needed
}

