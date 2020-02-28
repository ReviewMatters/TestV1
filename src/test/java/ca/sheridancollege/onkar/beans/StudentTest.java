package ca.sheridancollege.onkar.beans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StudentTest {
	
	@Test
	public void testBuildStudent() {
		Student student = Student.builder()
						.id(Long.valueOf(10))
						.name("Gary")
						.build();
		
		assertThat(student.getId()).isEqualTo(10);
		assertThat(student.getName()).isEqualTo("Gary");
	}

}
