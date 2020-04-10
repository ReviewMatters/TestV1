package com.courseAid.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String userName;
	
	
	private String encryptedPassword;
	
	private String phoneNumber;
	
	private String email;
	
	private Byte enabled;
	
	@ManyToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<Course>(); 
	
	@OneToOne(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
	private Role role;
}
