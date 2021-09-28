package org.example.SpringHibernate;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @RunWith(SpringRunner.class)
    @SpringBootTest
    @AutoConfigureMockMvc
    public class TestLogin {
        @Autowired
        private MockMvc mockMvc;

        //Finding text on the first page

        @Test
        public void contextLoads() throws Exception {
            this.mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello, world")))
                    .andExpect(content().string(containsString("Pavel Poliuskov Assignment")));
        }


        //testing the status of the webpage
        @Test
        public void loginTest() throws Exception {
            this.mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
//                    .andExpect(redirectedUrl("/http://localhost/login"));

        }

        //performing a log in
        @Test
        public void correctLoginTest() throws Exception {
            this.mockMvc.perform(formLogin().user("u").password("1"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/"));
        }

        //performing a log in with wrong credentials
        @Test
        public void badCredentialsTest() throws Exception {
            this.mockMvc.perform(post("/login").param("user", "wrongUserId"))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }


    }