package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	
	public Instructor findByLastName(String name);
	


}
