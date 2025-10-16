package com.example.demo.Entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty; // Note the change from javax to jakarta
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.stereotype.Component;

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
@Component
public class Publisher{
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int publisherId;
	 	@NotEmpty(message="Publisher Name can't be Empty.")
		@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
		@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
	 	private String name;
	 	
	 	@NotEmpty(message="Publisher's Address Name can't be Empty.")
		@Size(min=10,max=50,message="Minimum 10 and maximum 50 characters allowed.")
		@Pattern(regexp="^[a-zA-Z0-9\\s]*$",message="Only Alphabets Allowed")
	 	private String address;
	 	
	 	@NotEmpty(message = "please enter contactno ")
	 	@Size(min=10,max=10,message="Please Enter proper Contact number")
		@Pattern(regexp="^\\d{10}$",message="Only 10 Numbers Allowed")
	    private String contactNumber;
	    
	    @OneToMany(mappedBy = "publisher")
	    @JsonIgnore
		private List<Book> books;
}
