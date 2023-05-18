package com.ebanma.cloud.demo.testmethod;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.data.model.UserDO;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.domain.valueObject.User;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 方法2：模拟依赖对象
 */
public class Method2 {
    // 1.直接构建对象
    User user = new User(1L, "test");
    List<Long> userIdList = Arrays.asList(1L, 2L, 3L);
    // 2.反序列化对象，从资源文件中获取
    String resource;
    UserDO userDO1 = JSON.parseObject(resource, UserDO.class);
    List<UserDO> userDOList = JSON.parseArray(resource, UserDO.class);
    Map<Long, UserDO> userDOMap = JSON.parseObject(resource, new TypeReference<Map<Long, UserDO>>(){});
    // 3.利用 Mockito.mock 方法
    UserDO userDO2 = Mockito.mock(UserDO.class);
    List<Long> getUserIdList = (List<Long>)Mockito.mock(List.class);
    // 4.利用 @Mock 注解
    @Mock
    private UserDao userDao;
    // 5.利用 Mockito.spy 方法
    UserService userService1 = Mockito.spy(new UserService());
    // 6.利用 @Spy 注解
    @Spy
    private UserService userService2 = new UserService();
}