package org.example.SpringHibernate.controller;

import org.example.SpringHibernate.domain.Role;
import org.example.SpringHibernate.domain.User;
import org.example.SpringHibernate.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") //all Class methods (get or post) are referring to /user page
@PreAuthorize("hasAuthority('ADMIN')") //users can access this page only if they are ADMINs
public class UserController {
    @Autowired
    private UserRepo userRepo;


    //getting all users
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")//identifier
    public String userEditForm(@PathVariable User user, Model model) //requesting user from UserRepo
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSaveee(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet()); //role list (enum) to string

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));//adding ADMIN role
            }
        }

        userRepo.save(user);
        return "redirect:/user";

    }
}
