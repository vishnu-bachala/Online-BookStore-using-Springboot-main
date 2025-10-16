package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.Impl.MyUserDetailsService;


@RestController
// NOTE: @RequestMapping is often used here for a base path like "/api"
public class UserSignupController {

    @Autowired
    MyUserDetailsService myUserDetailService;

    // ❌ REMOVE THIS: The encoder is not needed here since the service handles it
    // @Autowired
    // BCryptPasswordEncoder bcryptPasswordEncorder;

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        Map<String, String> errors = new HashMap<>();
        String field = null;
        String message = null;
        try {
            // 🛑 FIX: REMOVE password encoding from the controller
            // String pwd = user.getPassword();
            // String bcryptpwd = bcryptPasswordEncorder.encode(pwd);
            // user.setPassword(bcryptpwd);

            User savedUser = myUserDetailService.addUser(user); // Password encoding happens inside the service
            return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
        } catch (Exception ex) {

            field = "Registration Failed";
            message = ex.getMessage();

            errors.put(field, message);
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return myUserDetailService.getUserById(id); // Requires public getUserById in service
    }

    @DeleteMapping("/users/{id}")
    public String deleteuser(@PathVariable int id) {
        myUserDetailService.deleteuser(id); // Requires public deleteuser in service
        return "User is deleted";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return myUserDetailService.getAllUsers(); // Requires public getAllUsers in service
    }
}