package com.ebanma.cloud.demo.testmethod;

import com.ebanma.cloud.demo.domain.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

/**
 * 方法1：定义测试对象
 */
public class Method1 {
    // 1.直接构建对象
    UserService userService1 = new UserService();
    // 2.利用 Mockito.spy 方法
    UserService userService2 = Mockito.spy(UserService.class);
    // 3.利用 @Spy 注解
    @Spy
    private UserService userService3 = new UserService();
    // 4.利用 @InjectMocks 注解
    @InjectMocks
    private UserService userService4;
}