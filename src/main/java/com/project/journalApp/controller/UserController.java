package com.project.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journalApp.entity.User;
import com.project.journalApp.repository.UserRepository;
import com.project.journalApp.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        // Retrieve the authenticated user's details from the Security Context
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Ensure the authentication object is valid
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
        }
        String authenticatedUserName = authentication.getName(); // Extract authenticated username
        // Fetch the user from the database
        User userInDb = userService.findByUserName(authenticatedUserName);
        if (userInDb == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword()); // Ensure password is hashed before saving
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    


}
