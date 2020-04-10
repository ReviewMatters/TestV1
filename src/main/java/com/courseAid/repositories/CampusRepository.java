package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Campus;


public interface CampusRepository extends JpaRepository<Campus, Long> {
	
	public Campus findByName(String name);

}
