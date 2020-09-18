package org.example.SpringHibernate.controller;

import org.example.SpringHibernate.domain.Role;
import org.example.SpringHibernate.domain.User;
import org.example.SpringHibernate.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        //if user already exists in DB, return message "user exists" and sending user back to /registration page
        if (userFromDb != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        //setting up the user
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); //setting role with only one - "USER" (singleton)
        userRepo.save(user); //saving user

        //on successful login redirecting to /login page
        return "redirect:/login";
    }
}
