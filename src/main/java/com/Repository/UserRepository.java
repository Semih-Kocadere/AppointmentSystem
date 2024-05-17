package com.Repository;

import com.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be added here if needed
    List<User> findById(long id);

   User findByName(String name);


}
