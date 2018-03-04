package com.rahmania.repository;

import com.rahmania.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bahaa on 10/02/18.
 */

@Repository
public interface ConstraintsRepository extends JpaRepository<Rule, Long>{
}
