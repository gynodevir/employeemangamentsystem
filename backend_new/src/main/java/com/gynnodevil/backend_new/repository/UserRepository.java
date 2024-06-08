package com.gynnodevil.backend_new.repository;

import com.gynnodevil.backend_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {


}
