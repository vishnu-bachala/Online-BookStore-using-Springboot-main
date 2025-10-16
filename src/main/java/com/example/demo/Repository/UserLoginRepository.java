package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;
@Repository
public interface UserLoginRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
	public Optional<User> findByEmail(String email);

}
