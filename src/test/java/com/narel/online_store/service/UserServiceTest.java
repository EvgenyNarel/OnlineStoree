package com.narel.online_store.service;

import com.narel.online_store.dao.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/create.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createUser() {
        User user = new User();
        user.setUsername("bill");

        Assert.assertNotNull(user.getUsername());
        Assert.assertEquals("bill", user.getUsername());
    }

    @Test
    public void showUsers() {
        User user1 = new User();
        user1.setUsername("Jeck");

        User user2 = new User();
        user2.setUsername("Denis");
        String expect = user1.getUsername();
        String actual = (userService.loadUserByUsername("Jeck").getUsername());

        Assert.assertNotNull(user1);
        Assert.assertNotNull(user2);
        Assert.assertEquals(expect,actual);

    }

}