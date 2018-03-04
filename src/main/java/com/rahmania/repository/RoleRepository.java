package com.rahmania.repository;

import com.rahmania.entity.Menue;
import com.rahmania.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bahaa on 26/01/18.
 */


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("select r.menueSet from Role r where  r.name = :role")
    List<Menue> loadUserMenue(@Param("role") String role);
}
