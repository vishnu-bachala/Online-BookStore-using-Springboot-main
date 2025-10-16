package com.example.demo.Service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserLoginRepository;



@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired

	UserLoginRepository userLoginRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userLoginRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not available in database");
		}
		return new UserPrincipal(user);
	}

	public User addUser(User u) {
		Optional<User> u1=userLoginRepository.findByEmail(u.getEmail());
		if(u1.isPresent()) {
			throw new UsernameNotFoundException("User already registered with the email "+u.getEmail()+" so please login or Enter new Email Id");
		}else {

			Set<Role> roles = new HashSet<>();
			roles.add(new Role(0, "User"));
			u.setRoles(roles);
		return userLoginRepository.save(u);
		}
	}


	public User addAdmin(User u) {
		Optional<User> u1=userLoginRepository.findByEmail(u.getEmail());
		if(u1.isPresent()) {
			throw new UsernameNotFoundException("User already registered with the email "+u.getEmail()+" so please login or Enter new Email Id");
		}else {

		return userLoginRepository.save(u);
		}
	}


	public List<User> getAllUsers() {
		return userLoginRepository.findAll();

	}

	public User getUserById(int id) {
		return userLoginRepository.findById(id).get();

	}

	public void deleteuser(int id) {
		userLoginRepository.deleteById(id);
	}
	}
