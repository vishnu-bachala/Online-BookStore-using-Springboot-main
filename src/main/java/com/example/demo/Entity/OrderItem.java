package com.example.demo.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;
    
    @NotNull(message = "Enter the Quantity of the Book you need.")
    private Integer quantity;
   
    @NotNull(message = "Enter Your Customer's Id.")
    private Integer cust_id;
    
    @NotNull(message = "Enter the BookID of the Book you want to Purchase.")
    private Integer purchasebook_id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    private Integer totalprice;			// Total OrderPrice will be Automatically Generated
    
    private String orderDate;			//date and time will be Automatically Generated
    
    private String DeliveryStatus;		//Will be Pending at generation but can be updated to delivered by the Admin
}
