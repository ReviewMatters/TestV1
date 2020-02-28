package ca.sheridancollege.onkar.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	private Long id;
	private String name;
	private String lastName;
}
