package com.ebanma.cloud.demo.domain.entity;

import org.junit.Assert;
import org.junit.Test;
public class UserVOTest {

    @Test
    public void testUserVO() {
        Long id = 12345L;
        String name = "test";
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName(name);
        userVO.setDescription("desc");
        Assert.assertEquals("用户标识不一致", id, userVO.getId());
        Assert.assertEquals("用户名称不一致", name, userVO.getName());
        //Assert.assertEquals("toString不一致", "UserVO{id='12345', name='test', description='desc'}", userVO.toString());
    }

}