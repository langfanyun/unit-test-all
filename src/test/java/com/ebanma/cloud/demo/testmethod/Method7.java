package com.ebanma.cloud.demo.testmethod;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.domain.service.IdGenerator;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.domain.valueObject.User;
import com.ebanma.cloud.demo.testdemo.NumberHelper;
import com.ebanma.cloud.demo.util.ResourceHelper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 方法7：验证数据对象
 */
public class Method7 {
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private UserService userService;

    /**
     * 1.验证数据对象空值
     */
    public void verifyNull() throws Exception {
        Long userId = 123L;

        // 1.验证数据对象为空
        Assert.assertNull("用户标识必须为空", null);

        // 2.验证数据对象非空
        Assert.assertNotNull("用户标识不能为空", userId);
    }

    /**
     * 2.验证数据对象布尔值
     */
    public void verifyBoolean() throws Exception {
        // 1.验证数据对象为真
        Assert.assertTrue("返回值必须为真", NumberHelper.isPositive(1));

        // 2.验证数据对象为假
        Assert.assertFalse("返回值必须为假", NumberHelper.isPositive(-1));
    }

    /**
     * 3.验证数据对象引用
     */
    public void verifyReference() throws Exception {
        User expectedUser = new User();
        User actualUser = new User();

        // 1.验证数据对象一致
        Assert.assertSame("用户必须一致", expectedUser, actualUser);

        // 2.验证数据对象不一致
        Assert.assertNotSame("用户不能一致", expectedUser, actualUser);
    }

    /**
     * 4.验证数据对象值
     */
    public void verifyValue() throws Exception {
        String userName = "admin";
        double accountAmount = 10000.0D;
        Long[] userIds = {1L, 2L, 3L};
        List<Long> userIdList = new ArrayList<>();
        User user = new User(1L, "admin");
        List<User> expectedUserList = Arrays.asList(new User(1L, "admin"), new User(2L, "member"));
        List<User> actualUserList = Arrays.asList(new User(1L, "admin"), new User(2L, "member"));

        // 1.验证简单数据对象
        Assert.assertNotEquals("用户名称不一致", "admin", userName);
        Assert.assertEquals("账户金额一致", 10000.0D, accountAmount, 1E-6D);

        // 2.验证简单数组或集合对象
        Assert.assertArrayEquals("用户标识列表不一致", new Long[]{1L, 2L, 3L}, userIds);
        Assert.assertEquals("用户标识列表不一致", Arrays.asList(1L, 2L, 3L), userIdList);

        // 3.验证复杂数据对象
        Assert.assertEquals("用户标识不一致", Long.valueOf("1"), user.getId());
        Assert.assertEquals("用户标识不一致", "admin", user.getName());

        // 4.验证复杂数组或集合对象
        Assert.assertEquals("用户列表长度不一致", expectedUserList.size(), actualUserList.size());

        // 5.通过序列化验证数据对象
        String text = ResourceHelper.getResourceAsString(getClass(), "userList.json");
        Assert.assertEquals("用户列表不一致", text, JSON.toJSONString(actualUserList));

        // 6.验证数据对象私有熟悉字段
        Assert.assertEquals("基础包不一致", "com.alibaba.demo", Whitebox.getInternalState(user, "basePackage"));
    }

    /**
     * 5.验证异常对象内容
     * 5.1 验证数据对象为空
     * 注意：一旦被测方法抛出异常后，整个方法就返回了，后面的验证依赖方法就没有办法执行了
     */
//    @Test(expected = RuntimeException.class)
    public void testGetUserWithExpected() {
        // 模拟依赖方法
        Long userId = 123L;
//        Mockito.doReturn(null).when(userDao).get(userId);

        // 调用被测方法
//        userService.getUser(userId);
    }

    /**
     * 5.验证异常对象内容
     * 5.2 通过 @Rule 注解验证异常对象
     */
//    @Rule
//    private RuntimeException exception = RuntimeException.none();
//    @Test
    public void testGetUserWithRule() {
        // 模拟依赖方法
        Long userId = 123L;
//        Mockito.doReturn(null).when(userDao).get(userId);

        // 调用被测方法
//        exception.expect(ExampleException.class);
//        exception.expectMessage(String.format("用户(%s)不存在", userId));
//        userService.getUser(userId);
    }

    /**
     * 5.验证异常对象内容
     * 5.3 通过 验证异常对象（推荐）
     */
//    @Test
    public void testGetUserWithAssert() {
        // 模拟依赖方法
        Long userId = 123L;
//        Mockito.doReturn(null).when(userDao).get(userId);

        // 调用被测方法
//        RuntimeException exception = Assert.assertThrows("异常类型不一致", RuntimeException.class, () -> userService.getUser(userId));
//        Assert.assertEquals("异常消息不一致", "处理异常", exception.getMessage());
    }
}