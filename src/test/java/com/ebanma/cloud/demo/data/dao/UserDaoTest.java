package com.ebanma.cloud.demo.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DaoApplication.class})
public class UserDaoTest {
    @Resource
    private UserDao userDAO;

    @Test
    public void testGetIdByName() {
        Long userId = 1L;
        String userName = "test";
        Assert.assertEquals("用户标识不一致", userId, userDAO.getIdByName(userName));
    }

    @Test
    public void testDelete() {
        Long userId = 1L;
        Assert.assertEquals("删除数量不一致", Integer.valueOf(1), userDAO.delete(userId));
    }

}