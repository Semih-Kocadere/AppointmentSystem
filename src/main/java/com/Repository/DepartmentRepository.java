package com.Repository;

import com.Entity.Department;
import com.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Custom query methods can be added here if needed
    Department findByName(String name);

    List<Department> findByHospitalId(Long hospitalId);

    Department findByNameAndHospitalName(String name, String hospitalName);

    List<Department> findByNameStartingWith(String prefix);

    List<Department> findByNameEndingWith(String suffix);

    List<Department> findByNameContaining(String infix);

    List<Department> findAllByOrderByNameAsc();

    Hospital findByHospitalName(String hospitalName);


}

