package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findByUserName(String userName);
	
    Student findByuserNameIgnoreCase(String userName);
    
    Student findByEmailIgnoreCase(String email);
        
}
