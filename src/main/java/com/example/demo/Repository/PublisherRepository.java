package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Publisher;
import com.example.demo.Entity.User;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	public Optional<Publisher> findBycontactNumber(String contactNumber);

}
