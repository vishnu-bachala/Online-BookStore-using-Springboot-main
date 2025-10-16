package com.example.demo.Entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CollectionId;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Customer{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
	@NotEmpty(message="first Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
    private String firstName;
	@NotBlank(message="Last Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
    private String lastName;
	
	@NotEmpty(message="Email can't be Empty. Please Enter Email id.")
	@Email(message = "Please Enter proper Email Id.")
    private String email;
	
	@NotEmpty(message="Contact number can't be Empty.")
	@Size(min=10,max=10,message="Please Enter proper 10 digit mobile number")
	@Pattern(regexp="^\\d{10}$",message="Only 10 Numbers Allowed")
    private String phoneNumber;
	
	@NotEmpty(message=" Customer Address can't be Empty.")
	@Size(min = 10,max = 50,message="Minimum 10 and maximum 50 characters allowed.")
	private String address;
//   @JsonIgnore
//	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//	private List<OrderItem> orderItem;
}
