package com.Repository;

import com.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Custom query methods can be added here if needed
    Doctor findByName(String name);

    List<Doctor> findByDepartmentId(Long departmentId);

    List<Doctor> findByHospitalId(Long hospitalId);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByNameAndDepartmentId(String name, Long departmentId);

    List<Doctor> findByNameStartingWith(String prefix);

    List<Doctor> findByNameEndingWith(String suffix);

    List<Doctor> findByNameContaining(String infix);

    List<Doctor> findAllByOrderByNameAsc();

    Doctor getDoctorById(Long id);
}
