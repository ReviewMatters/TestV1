package com.courseAid.beans;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Review {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private LocalDateTime dateReviewed;
	
	@Min(1)
	@Max(5)
	private int rating;
	
	private String title;
	
	private String reviewBody;
	
	private Boolean isAnonymous;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private Course course;
	
	@OneToOne 
	private Instructor instructor;
	
	

}
