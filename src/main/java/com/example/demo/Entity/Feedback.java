package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackid;
	
	 @Min(value = 1, message = "Rating value must be at least 1")
	 @Max(value = 5, message = "Rating value must not exceed 5")
	    private double ratings;
	
	@NotNull(message = "Please enter Book Id for providing Feedback.")
	private Integer Bookid;

	@NotNull(message = "Please enter your Thoughts and Feedback of the Book.")
	@Size(min=3,message="Minimum 3 characters allowed.")
	private String review;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Book book;
}
