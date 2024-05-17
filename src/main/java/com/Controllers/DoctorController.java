package com.Controllers;

import com.DTO.DoctorDetailsDTO;
import com.Entity.Doctor;
import com.Entity.Hospital;
import com.Services.DepartmentService;
import com.Services.DoctorService;
import com.Services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }

        DoctorDetailsDTO dto = new DoctorDetailsDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setDepartmentName(doctor.getDepartment().getName());
        dto.setHospitalName(doctor.getHospital().getName());
        dto.setPhone(doctor.getPhone());
        dto.setEmail(doctor.getEmail());
        dto.setGender(doctor.getGender());
        dto.setAge(doctor.getAge());
        dto.setQualification(doctor.getQualification());
        dto.setExperience(doctor.getExperience());

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDoctor(@RequestParam String name,
                                             @RequestParam String specialization,
                                             @RequestParam String departmentName,
                                             @RequestParam String hospitalName,
                                             @RequestParam String phone,
                                             @RequestParam String email,
                                             @RequestParam String gender,
                                             @RequestParam int age,
                                             @RequestParam String qualification,
                                             @RequestParam int experience) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setDepartment(departmentService.getDepartmentByNameAndHospitalName(departmentName, hospitalName));
        doctor.setHospital(hospitalService.getHospitalByName(hospitalName));
        doctor.setPhone(phone);
        doctor.setEmail(email);
        doctor.setGender(gender);
        doctor.setAge(age);
        doctor.setQualification(qualification);
        doctor.setExperience(experience);

        doctorService.saveDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable (required = false) Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String specialization,
                                             @RequestParam(required = false) String departmentName,
                                             @RequestParam(required = false) String hospitalName,
                                             @RequestParam(required = false) String phone,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String gender,
                                             @RequestParam(required = false) Integer age,
                                             @RequestParam(required = false) String qualification,
                                             @RequestParam(required = false) Integer experience) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (name != null && !name.isEmpty()) {
            doctor.setName(name);
        }
        if (specialization != null && !specialization.isEmpty()) {
            doctor.setSpecialization(specialization);
        }
        if (hospitalName != null && !hospitalName.isEmpty()) {
            doctor.setHospital(hospitalService.getHospitalByName(hospitalName));
        }else{hospitalName= doctor.getHospital().getName();}

        if (departmentName != null && !departmentName.isEmpty()) {
            doctor.setDepartment(departmentService.getDepartmentByNameAndHospitalName(departmentName, hospitalName));
        }
        if (phone != null && !phone.isEmpty()) {
            doctor.setPhone(phone);
        }
        if (email != null && !email.isEmpty()) {
            doctor.setEmail(email);
        }
        if (gender != null && !gender.isEmpty()) {
            doctor.setGender(gender);
        }
        if (age != null) {
            doctor.setAge(age);
        }
        if (qualification != null && !qualification.isEmpty()) {
            doctor.setQualification(qualification);
        }
        if (experience != null) {
            doctor.setExperience(experience);
        }

        doctorService.saveDoctor(doctor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    // Other endpoints as needed
}
