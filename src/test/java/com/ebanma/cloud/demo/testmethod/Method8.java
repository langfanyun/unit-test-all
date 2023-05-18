package com.ebanma.cloud.demo.testmethod;

import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.domain.service.IdGenerator;
import com.ebanma.cloud.demo.domain.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * 方法8：验证依赖对象
 */
public class Method8 {
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private UserService userService;

    public void demo() throws Exception {
        // 1.验证模拟对象没有任何方法调用
        Mockito.verifyNoInteractions(idGenerator, userDao);

        // 2.验证模拟对象没有更多方法调用
        Mockito.verifyNoMoreInteractions(idGenerator, userDao);
        Mockito.verifyZeroInteractions(idGenerator, userDao); // 已废弃

        // 3.清除模拟对象所有方法调用标记
        // 3.1.清除所有对象调用
        Mockito.clearInvocations();
        // 3.2.清除指定对象调用
        Mockito.clearInvocations(idGenerator, userDao);
    }

}