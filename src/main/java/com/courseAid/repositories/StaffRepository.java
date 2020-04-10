package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	public Staff findByUserName(String userName);
}
