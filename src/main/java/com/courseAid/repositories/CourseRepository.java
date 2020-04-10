package com.courseAid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.courseAid.beans.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	public List<Course> findByName(String name);
	
	
	public List<Course> findByNameContainingIgnoreCase(String name);
	
	
//	  /**
//     * Finds all courses by using the last name as a search criteria.
//     * @param name
//     * @return  A list of courses whose name match with the given name
//     *          If no course is found, this method returns an empty list.
//     */
//    @Query("SELECT * FROM Course c WHERE LOWER(c.name) like LOWER(:name)")
//    public List<Course> findByMatchingName(@Param("name") String name);
	
}
