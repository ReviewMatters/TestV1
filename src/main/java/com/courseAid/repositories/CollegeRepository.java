package com.courseAid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.College;
import com.courseAid.beans.Instructor;

public interface CollegeRepository extends JpaRepository<College, Long> {
	
	public College findByName(String name);
	public List<College> findByInstructors(Instructor instructor);

}
