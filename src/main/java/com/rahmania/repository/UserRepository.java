package com.rahmania.repository;

import com.rahmania.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by bahaa on 26/01/18.
 */
@Repository
public interface UserRepository extends JpaRepository<Users , Long> {

   Users findByMobileNumber(String mobileNumber);
   Users findByMobileNumberAndActiveIsTrue(String mobileNumber);
}
