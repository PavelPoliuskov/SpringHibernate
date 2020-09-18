package org.example.SpringHibernate;


import org.example.SpringHibernate.domain.Message;
import org.example.SpringHibernate.domain.Role;
import org.example.SpringHibernate.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

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

    @Test
    public void addUser(){

        Assert.assertEquals(user.getUsername(), "u" );
        Assert.assertEquals(user.getPassword(), "1");
        Assert.assertEquals(user.getRoles().toString(), "[USER]");
        Assert.assertEquals(user.getId(), Long.valueOf(1));
    }

    @Test
    public void addMessage(){

        Assert.assertEquals(message.getAuthorName(),"u");
        Assert.assertEquals(message.getText(), "text");
        Assert.assertEquals(message.getTag(), "tag");
        Assert.assertEquals(message.getFilename(), "filename");

    }


}
