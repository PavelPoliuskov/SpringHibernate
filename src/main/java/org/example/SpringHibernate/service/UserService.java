package org.example.SpringHibernate.service;

import org.example.SpringHibernate.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //=component
public class UserService implements UserDetailsService {
    //connecting to user database
    @Autowired
    private UserRepo userRepo;

    //getting users by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
