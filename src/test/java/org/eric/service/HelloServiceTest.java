package org.eric.service;

import org.eric.SuperTest;
import org.eric.model.Customer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HelloServiceTest extends SuperTest {

    @Autowired
    private HelloService helloService;

    @Test
    @Ignore
    public void testTransaction() {

        Customer result = null;
        try {
            result = helloService.addCustomer("eric", "shih");

            Assert.assertNotNull(result);
            Assert.assertTrue(result.getId() > 0);
        } catch (Exception e) {
            Assert.fail();
        }


    }
}
