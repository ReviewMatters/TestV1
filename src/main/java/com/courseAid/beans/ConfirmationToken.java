package com.courseAid.beans;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String confirmationToken;
    
    private LocalDateTime createdDate;
    
    @OneToOne
    private Student student;
    
    public ConfirmationToken(Student student) {
        this.student = student;
        createdDate = LocalDateTime.now();
        confirmationToken = UUID.randomUUID().toString();
    }
    
}
