package com.courseAid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRolename(String rolename);
	
}
