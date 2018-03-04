package com.rahmania.repository;

import com.rahmania.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bahaa on 10/02/18.
 */
@Repository
public interface AboutRepository extends JpaRepository<About ,Long> {
}
