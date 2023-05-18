package com.ebanma.cloud.demo.testmethod;

import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.data.model.UserDO;
import com.ebanma.cloud.demo.domain.service.IdGenerator;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.domain.valueObject.User;
import com.ebanma.cloud.demo.service.OrderApplicationService;
import com.ebanma.cloud.demo.testdemo.NumberHelper;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

import static org.mockito.Mockito.times;

/**
 * 方法6：验证依赖方法
 */
public class Method6 {
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private UserService userService;

    /**
     * 1.根据参数验证方法调用
     */
    public void verifyByParam() throws Exception {
        Long userId = 123L;

        // 1.验证无参数方法调用
        Mockito.verify(userDao).hashCode();

        // 2.验证指定参数方法调用
        Mockito.verify(userDao).delete(userId);

        // 3.验证任意参数方法调用
        Mockito.verify(userDao).delete(Mockito.anyLong());

        // 4.验证可空参数方法调用
//        Mockito.verify(userDao).queryCompany(Mockito.anyLong(), Mockito.nullable(Long.class));

        // 5.验证必空参数方法调用
//        Mockito.verify(userDao).queryCompany(Mockito.anyLong(), Mockito.isNull());

        // 6.验证可变参数方法调用
//        Mockito.verify(userService).delete(Mockito.any(Long.class));
//        Mockito.verify(userService).delete(1L, 2L, 3L); // 匹配一个
//        Mockito.verify(userService).delete(Mockito.<Long>any()); // 匹配多个
    }

    /**
     * 2.验证方法调用次数
     */
    public void verifyCallTimes() throws Exception {
        int n = 10;
        Long userId = 123L;

        // 1.验证方法默认调用 1 次
        Mockito.verify(userDao).delete(userId);

        // 2.验证方法从不调用
        Mockito.verify(userDao, Mockito.never()).delete(userId);

        // 3.验证方法调用 n 次
        Mockito.verify(userDao, times(n)).delete(userId);

        // 4.验证方法调用至少 1 次
        Mockito.verify(userDao, Mockito.atLeastOnce()).delete(userId);

        // 5.验证方法调用至少 n 次
        Mockito.verify(userDao, Mockito.atLeast(n)).delete(userId);

        // 6.验证方法调用最多 1 次
        Mockito.verify(userDao, Mockito.atMostOnce()).delete(userId);

        // 7.验证方法调用最多 n 次
        Mockito.verify(userDao, Mockito.atMost(n)).delete(userId);

        // 8.验证方法调用指定 n 次
        Mockito.verify(userDao, Mockito.calls(n)).delete(userId);

        // 9.验证对象及其方法调用 1 次
        Mockito.verify(userDao, Mockito.only()).delete(userId);
    }

    /**
     * 3.验证方法调用并捕获参数
     */
    public void verifyAndCaptor() throws Exception {
        // 1.使用 ArgumentCaptor.forClass 方法定义参数捕获器
        ArgumentCaptor<UserDO> userCaptor = ArgumentCaptor.forClass(UserDO.class);
        Mockito.verify(userDao).modify(userCaptor.capture());
        UserDO user = userCaptor.getValue();

        // 2.使用 @Captor 注解定义参数捕获器

        // 3.捕获多次方法调用的参数值列表
        userCaptor = ArgumentCaptor.forClass(UserDO.class);
        Mockito.verify(userDao, Mockito.atLeastOnce()).modify(userCaptor.capture());
        List<UserDO> userDOList = userCaptor.getAllValues();
    }

    // 2.使用 @Captor 注解定义参数捕获器
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private OrderApplicationService orderApplicationService;
    /**
     * 4.验证其他特殊方法
     */
    public void verifyOthers() throws Exception {
        // 1.验证 final 方法调用
        // final 方法的验证跟普通方法类似

        // 2.验证私有方法调用
        PowerMockito.verifyPrivate(orderApplicationService, times(1)).invoke("getUserFromExternalSystem", User.class, Mockito.<Long>any());

        // 3.验证构造方法调用
        PowerMockito.verifyNew(User.class).withNoArguments();
        PowerMockito.verifyNew(User.class).withArguments(1L, "admin");

        // 4.验证静态方法调用
        PowerMockito.verifyStatic(NumberHelper.class);
        NumberHelper.isPositive(-1);
    }

}