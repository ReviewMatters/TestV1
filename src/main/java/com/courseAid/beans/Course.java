package com.courseAid.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.courseAid.beans.College.CollegeBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Department department;
	
	@ManyToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
    private List<Instructor> instructors =  new ArrayList<Instructor>(); 
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="courses")
    private List<Student> students;
	
	
	

	
}
