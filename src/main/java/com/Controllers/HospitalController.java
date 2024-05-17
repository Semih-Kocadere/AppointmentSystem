package com.Controllers;
import com.Entity.Appointment;
import com.Entity.Hospital;
import com.Repository.HospitalRepository;
import com.Services.HospitalService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository, HospitalService hospitalService) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id){
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(hospital);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveHospital(@ModelAttribute Hospital hospital,
                                             @RequestParam String name,
                                             @RequestParam String address) {
        hospital.setName(name);
        hospital.setAddress(address);
        hospitalService.saveHospital(hospital);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHospital(@PathVariable Long id,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String address) {
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (name != null) {
            hospital.setName(name);
        }
        if (address != null) {
            hospital.setAddress(address);
        }
        hospitalService.saveHospital(hospital);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        if (!hospitalService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hospitalService.deleteHospital(id);
        return ResponseEntity.ok().build();
    }


}
