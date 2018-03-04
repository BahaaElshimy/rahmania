package com.rahmania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rahmania.entity.Menue;

@Repository
public interface MenuRepository extends JpaRepository<Menue, Long> {

	@Modifying(clearAutomatically = true)
	@Query("Update Menue m SET m.disabled = true WHERE m.url = '/test' ")
	void disableTestMenu();

}
