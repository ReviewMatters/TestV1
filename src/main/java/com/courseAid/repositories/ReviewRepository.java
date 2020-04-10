package com.courseAid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseAid.beans.Course;
import com.courseAid.beans.Review;
import com.courseAid.beans.Student;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findByCourse(Course course);
	public List<Review> findByStudent(Student student);
	public Review findByTitle(String title); //Needed for test.
	public Review findByRating(int rating); //Needed for test.

}
