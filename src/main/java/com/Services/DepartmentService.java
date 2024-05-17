package com.Services;

import com.Entity.Department;

import java.util.List;

public interface DepartmentService {
    Department findById(Long id);
    Department findByName(String name);
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    void saveDepartment(Department department);
    void deleteDepartment(Long id);

    boolean existsById(Long id);

    Department getDepartmentByName(String name);

    Department getDepartmentByNameAndHospitalName(String name, String hospitalName);


    // Other methods as needed
}

