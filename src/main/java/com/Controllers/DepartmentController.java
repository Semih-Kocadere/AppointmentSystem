package com.Controllers;
import com.Entity.Department;
import com.Entity.Doctor;
import com.Entity.Hospital;
import com.Services.DepartmentService;
import com.Services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDepartment(@RequestParam Long id,
                                                 @RequestParam String name,
                                                 @RequestParam String hospitalName) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setHospital(hospitalService.getHospitalByName(hospitalName));

        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable Long id,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String hospitalName) {
        Department department = departmentService.getDepartmentById(id);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (name != null) {
            department.setName(name);
        }
        if (hospitalName != null) {
            department.setHospital(hospitalService.getHospitalByName(hospitalName));
        }

        departmentService.saveDepartment(department);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (!departmentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }

    // Other endpoints as needed
}
