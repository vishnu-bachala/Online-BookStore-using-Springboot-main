package com.example.demo.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "Bookroles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rid;
	@NotEmpty(message = "role can't be empty")
	private String rname;
	
}
