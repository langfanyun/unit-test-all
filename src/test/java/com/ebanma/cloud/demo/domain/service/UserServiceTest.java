package com.ebanma.cloud.demo.domain.service;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.data.model.UserDO;
import com.ebanma.cloud.demo.domain.entity.UserVO;
import com.ebanma.cloud.demo.util.ResourceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    /** 定义静态常量：资源路径 */
    private static final String RESOURCE_PATH = "testUserService/";
    /** 模拟依赖对象 */
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    /** 定义测试对象 */
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        Whitebox.setInternalState(userService, "canModify", Boolean.TRUE);
    }

    @Test
    public void testSaveUserWithCreate() {
        // 模拟依赖方法:userDao.getIdByName
        Mockito.doReturn(null).when(userDao).getIdByName(Mockito.anyString());
        // 模拟依赖方法:idGenerator.next
        Long userId = 123L;
        Mockito.doReturn(userId).when(idGenerator).next();

        // 调用测试方法
        String path = RESOURCE_PATH + "testSaveUserWithCreate/";
        String text = ResourceHelper.getResourceAsString(getClass(), path + "userSave.json");
        UserVO userSave = JSON.parseObject(text, UserVO.class);
        Assert.assertEquals("用户标识不一致", userId, userService.saveUser(userSave));

        // 验证依赖方法:userDao.getIdByName
        Mockito.verify(userDao).getIdByName(userSave.getName());
        // 验证依赖方法:idGenerator.next
        Mockito.verify(idGenerator).next();
        // 验证依赖方法:userDao.create
        ArgumentCaptor<UserDO> userCreateCaptor = ArgumentCaptor.forClass(UserDO.class);
        Mockito.verify(userDao).create(userCreateCaptor.capture());
        text = ResourceHelper.getResourceAsString(getClass(), path + "userCreate.json");
        Assert.assertEquals("用户创建不一致", text, JSON.toJSONString(userCreateCaptor.getValue()));

        // 验证依赖对象
        Mockito.verifyNoMoreInteractions(idGenerator, userDao);
    }
}