package com.Repository;

import com.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

    User findByPassword(String password);

    User findByName(String name);

}
