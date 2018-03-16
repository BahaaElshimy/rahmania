package com.rahmania.repository;

import com.rahmania.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bahaa on 26/01/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByMobileNumber(String mobileNumber);
   User findByMobileNumberAndActiveIsTrue(String mobileNumber);
}
