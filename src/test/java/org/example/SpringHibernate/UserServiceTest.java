package org.example.SpringHibernate;


import org.example.SpringHibernate.domain.Message;
import org.example.SpringHibernate.domain.Role;
import org.example.SpringHibernate.domain.User;
import org.example.SpringHibernate.repos.UserRepo;
import org.example.SpringHibernate.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;
    User user = new User();
    Message message = new Message();

    @Before
    public void setUp(){
        user.setUsername("u");
        user.setPassword("1");
        user.setRoles(Collections.singleton(Role.USER));
        user.setId((long) 1);

        message.setText("text");
        message.setTag("tag");
        message.setAuthor(user);
        message.setFilename("filename");
    }

    //testing a user
    @Test
    public void addUser(){

        Assert.assertNotNull(user.getUsername());
        Assert.assertEquals(user.getUsername(), "u" );
        Assert.assertEquals(user.getPassword(), "1");
        Assert.assertEquals(user.getRoles().toString(), "[USER]");
        Assert.assertEquals(user.getId(), Long.valueOf(1));
    }


    //testing a message
    @Test
    public void addMessage(){

        Assert.assertEquals(message.getAuthorName(),"u");
        Assert.assertEquals(message.getText(), "text");
        Assert.assertEquals(message.getTag(), "tag");
        Assert.assertEquals(message.getFilename(), "filename");

    }

    //testing a connection to the DB
    @Test
    public void getUserByUserNameTrue () {
        Assert.assertNull(userRepo.findByUsername("t"));
        Assert.assertEquals(userRepo.findByUsername("u").getUsername(), "u");
    }
}
