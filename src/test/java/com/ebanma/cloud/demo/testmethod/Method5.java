package com.ebanma.cloud.demo.testmethod;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.domain.entity.UserVO;
import com.ebanma.cloud.demo.domain.service.IdGenerator;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.domain.valueObject.User;
import com.ebanma.cloud.demo.testdemo.NumberHelper;
import com.ebanma.cloud.demo.util.ResourceHelper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

/**
 * 方法5：调用被测方法
 */
public class Method5 {
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private UserService userService;

    /**
     * 1.调用构造方法
     */
    public void callConstructor() throws Exception {
        // 1.调用有访问权限的构造方法
        User user1 = new User();
        User user2 = new User(1L, "admin");

        // 2.调用无访问权限的构造方法
        Whitebox.invokeConstructor(ResourceHelper.class);
        Whitebox.invokeConstructor(User.class, 1L, "admin");
    }

    /**
     * 2.调用普通方法
     */
    public void callNormalMethod() throws Exception {
        UserVO user = null;
        Long userId = 123L;

        // 1.调用有访问权限的普通方法
        userService.saveUser(user);

        // 2.调用无访问权限的普通方法
        Whitebox.invokeMethod(userService, "isSuper", userId);
    }

    /**
     * 3.调用静态方法
     */
    public void callStaticMethod() throws Exception {
        // 1.调用有访问权限的普通方法
        boolean positive = NumberHelper.isPositive(-1);

        // 2.调用无访问权限的普通方法
        Whitebox.invokeMethod(JSON.class, "toJSONString", new Object());
    }

}