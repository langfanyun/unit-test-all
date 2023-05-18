package com.ebanma.cloud.demo.testmethod;

import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.util.FieldHelper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * 方法3：注入依赖对象
 */
public class Method3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    // 4.利用 @InjectMocks 注解注入
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    // 1.利用 Setter 方法注入
    // UserService.setMaxCount(100);
    // UserService.setUserDao(userDao);

    public void demo() throws Exception {
        // 2.利用 ReflectionTestUtils.setField 方法注入
        ReflectionTestUtils.setField(userService, "maxCount", 100);
        ReflectionTestUtils.setField(userService, "userDao", userDao);

        // 3.利用 Whitebox.setInternalState 方法注入
        Whitebox.setInternalState(userService, "maxCount", 100);
        Whitebox.setInternalState(userService, "userDao", userDao);

        // 5.设置静态常量字段值
        FieldHelper.setStaticFinalField(UserService.class, "LOGGER", LOGGER);
    }

}