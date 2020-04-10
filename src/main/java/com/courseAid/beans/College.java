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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.courseAid.beans.Instructor.InstructorBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder



public class College {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="college_id")
	private Long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
	@JoinColumn(name ="college_id", referencedColumnName = "college_id")
    private List<Campus> campusList = new ArrayList<Campus>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy="colleges")
    private List<Instructor> instructors = new ArrayList<Instructor>();
	 
 
	
}
