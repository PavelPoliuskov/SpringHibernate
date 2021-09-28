package org.example.SpringHibernate;

import org.example.SpringHibernate.domain.Role;
import org.example.SpringHibernate.domain.User;
import org.example.SpringHibernate.repos.UserRepo;
import org.example.SpringHibernate.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestMock {

    User user = new User();

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    UserService userService = new UserService();

    @Before
    public void setUp(){
        user.setUsername("u");
        user.setPassword("1");
        user.setRoles(Collections.singleton(Role.USER));
        user.setId((long) 1);
    }

    //Mocking the database and testing a UserService class
    @Test
    public void Check (){

        when(userRepo.findByUsername("u")).thenReturn(user);
        assertEquals("u", userService.loadUserByUsername("u").getUsername());
    }

}
