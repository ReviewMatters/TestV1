package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.ConfirmationToken;
import com.courseAid.beans.Student;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	
	public ConfirmationToken findByConfirmationToken(String confirmationToken);
	public ConfirmationToken findByStudent(Student student);
	
}
