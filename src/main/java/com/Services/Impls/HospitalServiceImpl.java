package com.Services.Impls;

import com.Entity.Hospital;
import com.Repository.HospitalRepository;
import com.Services.HospitalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.getById(id);
    }

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return hospitalRepository.existsById(id);
    }

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.findByName(name);
    }
}
