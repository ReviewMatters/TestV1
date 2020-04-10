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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.courseAid.beans.Student.StudentBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Instructor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;

	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column
	private Department department;
	
	@ManyToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
    private List<College> colleges = new ArrayList<College>(); ;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="instructors")
    private List<Course> courses;
	
	
	
	
}
