package com.ebanma.cloud.demo.testmethod;

import com.ebanma.cloud.demo.data.dao.UserDao;
import com.ebanma.cloud.demo.data.model.UserDO;
import com.ebanma.cloud.demo.domain.service.IdGenerator;
import com.ebanma.cloud.demo.domain.service.UserService;
import com.ebanma.cloud.demo.domain.valueObject.User;
import com.ebanma.cloud.demo.util.ResourceHelper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法4：模拟依赖方法
 */
public class Method4 {
    @Mock
    private UserDao userDao;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private UserService userService;

    /**
     * 1.根据返回模拟方法
     */
    public void mockByReturnDemo() throws Exception {
        Long userId = 123L;
        UserDO user = new UserDO();
        Map<Long, UserDO> userMap = new HashMap<>();

        // 1.模拟无返回值方法
        Mockito.doNothing().when(userDao).delete(userId);

        // 2.模拟方法单个返回值
//        Mockito.doReturn(user).when(userDao).get(userId);
//        Mockito.when(userDao.get(userId)).thenReturn(user);

        // 3.模拟方法多个返回值
//        Mockito.doReturn(record0, record1, record2, null).when(recordReader).read();
//        Mockito.when(recordReader.read()).thenReturn(record0, record1, record2, null);

        // 4.模拟方法定制返回值
//        Mockito.doAnswer(invocation -> userMap.get(invocation.getArgument(0))).when(userDao).get(Mockito.anyLong());
//        Mockito.when(userDao.get(Mockito.anyLong())).thenReturn(invocation -> userMap.get(invocation.getArgument(0)));

        // 5.模拟方法抛出单个异常
//        Mockito.doThrow(exception).when(userDao).get(Mockito.anyLong());
//        Mockito.when(userDao.get(Mockito.anyLong())).thenThrow(exception);

        // 6.模拟方法抛出多个异常
//        Mockito.doThrow(exception1, exception2).when(userDao).get(Mockito.anyLong());
//        Mockito.when(userDao.get(Mockito.anyLong())).thenThrow(exception1, exception2);

        // 7.直接调用真实方法
//        Mockito.doCallRealMethod().when(userService).getUser(userId);
//        Mockito.when(userService.getUser(userId)).thenCallRealMethod();
    }

    /**
     * 2.根据参数模拟方法
     */
    public void mockByParamDemo() throws Exception {
        Long userId = 123L;
        UserDO user = new UserDO();
        UserDO user1 = new UserDO();
        UserDO user2 = new UserDO();

        // 1.模拟无参数方法
//        Mockito.doReturn(deleteCount).when(userDao).deleteAll();
//        Mockito.when(userDao.deleteAll()).thenReturn(deleteCount);

        // 2.模拟指定参数方法
//        Mockito.doReturn(user).when(userDao).get(userId);
//        Mockito.when(userDao.get(userId)).thenReturn(user);

        // 3.模拟任意参数方法
        // Mockito提供了anyInt、anyLong、anyString、anyList、anySet、anyMap、any(Class clazz)等方法来表示任意值。
//        Mockito.doReturn(user).when(userDao).get(Mockito.anyLong());
//        Mockito.when(userDao.get(Mockito.anyLong())).thenReturn(user);

        // 4.模拟可空参数方法
//        Mockito.doReturn(user).when(userDao).queryCompany(Mockito.anyLong(), Mockito.nullable(Long.class));
//        Mockito.when(userDao.queryCompany(Mockito.anyLong(), Mockito.<Long>any())).thenReturn(user);

        // 5.模拟必空参数方法
//        Mockito.doReturn(user).when(userDao).queryCompany(Mockito.anyLong(), Mockito.isNull());
//        Mockito.when(userDao.queryCompany(Mockito.anyLong(), Mockito.eq(null))).thenReturn(user);

        // 6.模拟不同参数方法
//        Mockito.doReturn(user1).when(userDao).get(1L);
//        Mockito.doReturn(user2).when(userDao).get(2L);

        // 7.模拟可变参数方法
//        Mockito.when(userService.delete(Mockito.anyLong())).thenReturn(true);// 匹配一个
//        Mockito.when(userService.delete(Mockito.<Long>any())).thenReturn(true);// 匹配0或多个
//        Mockito.when(userService.delete(1L, 2L, 3L)).thenReturn(true);
    }

    /**
     * 3.根据其他特殊方法
     */
    public void mockByOtherDemo() throws Exception {
        Long userId = 123L;
        String userName = "userName";
        User user = new User();

        // 1.模拟 final 方法 (跟模拟普通方法完全一致，但是，需要把对应的模拟类添加到@PrepareForTest注解中)
        Mockito.doReturn(userId).when(idGenerator).next();
        Mockito.when(idGenerator.next()).thenReturn(userId);

        // 2.模拟私有方法
        PowerMockito.doReturn(true).when(UserService.class, "isSuper", userId);
        PowerMockito.when(UserService.class, "isSuper", userId).thenReturn(true);

        // 3.模拟构造方法
        PowerMockito.whenNew(User.class).withNoArguments().thenReturn(user);
        PowerMockito.whenNew(User.class).withArguments(userId, userName).thenReturn(user);

        // 4.模拟静态方法
        // 4.1.模拟静态类
        PowerMockito.mockStatic(ResourceHelper.class);
        PowerMockito.spy(ResourceHelper.class);

        // 4.2.模拟静态方法
        String resource = "", name = "";
        PowerMockito.when(ResourceHelper.getResourceAsString(getClass(), name)).thenReturn(resource);
        PowerMockito.doReturn(resource).when(ResourceHelper.class, "getResourceAsString", getClass(), name);
        PowerMockito.when(ResourceHelper.class, "getResourceAsString", getClass(), name).thenReturn(resource);
    }

}