package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message="Book Title can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
    private String title;

    @NotEmpty(message = "Author is required")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
    private String author;

    @NotNull(message="Publication Date can't be Empty.")
    @PastOrPresent
    private LocalDate publicationDate;

    @NotNull(message="Price  can't be Empty.")    
     private Integer price;
    
    @NotEmpty(message = "Description is required")
    @Size(max=250,message = "Please Enter Only Upto 250 characters.")
    private String description;
    
    @NotNull(message = "Quantity Availablle cant be empty.")
    @Min(value = 1, message = "Book Quantity must be at least 1")
    private Integer quantityAvailable;

    private Double userratings;
    
//   @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @NotNull(message = "Enter the CategoryId for the Book.")
    private Integer bookcategoryid;
   
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisherId")
    private Publisher publisher;
    
    
    @NotNull(message = "Enter the PublisherId for the Book.")
    private Integer bookpublisherid;
    
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
//	@JsonIgnore
	private List<Feedback> reviews;

}