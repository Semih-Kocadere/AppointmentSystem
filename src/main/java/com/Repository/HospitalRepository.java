package com.Repository;

import com.Entity.Hospital;
import com.Services.HospitalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // Custom query methods can be added here if needed

     Hospital findByName(String name);

    List<Hospital> findByAddress(String address);

    List<Hospital> findByNameStartingWith(String prefix);

    List<Hospital> findByNameEndingWith(String suffix);

    List<Hospital> findByNameContaining(String infix);

    List<Hospital> findAllByOrderByNameAsc();

    void deleteById(Long hospitalId);

    boolean existsById(Long hospitalId);

    Hospital getById (Long id);

}

