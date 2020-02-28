package ca.sheridancollege.onkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.sheridancollege.onkar.beans.Student;

@SpringBootApplication
public class LombokIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(LombokIntroApplication.class, args);
		
		Student student = new Student();
		student.setId(Long.valueOf(5));
		student.setName("Frank");
		System.out.println(student);
		
	}

}
