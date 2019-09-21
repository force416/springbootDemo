package org.eric.service;

import org.eric.SuperTest;
import org.eric.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class UserServiceTest extends SuperTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Rollback(true)
    public void test_add_user_should_success() {
        User user = new User();
        user.setId(123);
        user.setFirstName("eric");
        user.setLastName("shih");
        user.setUsername("eric_shih_is_good");

        User result = userService.addUser(user);

        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_add_exists_user_should_success() {
        User user = new User();
        user.setId(123);
        user.setFirstName("eric");
        user.setLastName("shih");
        user.setUsername("eric_shih_is_good");
        userService.addUser(user);

        User user2 = new User();
        user2.setId(123);
        user2.setFirstName("eric");
        user2.setLastName("shih");
        user2.setUsername("eric_shih_is_good");
        User result = userService.addUser(user2);

        Assert.assertNotNull(user2);
        Assert.assertEquals(123, result.getId());
        Assert.assertEquals("eric", result.getFirstName());
    }
}
