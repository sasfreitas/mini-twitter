package com.photonshoot.minitwitter.controller;

import com.photonshoot.minitwitter.config.AppConfig;
import com.photonshoot.minitwitter.domain.Users;
import com.photonshoot.minitwitter.model.User;
import com.photonshoot.minitwitter.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void listCallTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void listTest() {
        String username = "test";
        String email = "test@me.com";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepositoryMock.list()).thenReturn(users);

        Users usersResponse = userController.list();

        assertThat(usersResponse.getUsers().get(0).getUsername(), is(username));
        assertThat(usersResponse.getUsers().get(0).getEmail(), is(email));

        verify(userRepositoryMock, times(1)).list();
        verifyNoMoreInteractions(userRepositoryMock);
    }
}
