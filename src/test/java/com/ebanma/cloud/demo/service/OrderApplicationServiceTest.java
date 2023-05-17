package com.ebanma.cloud.demo.service;

import com.ebanma.cloud.demo.adapter.cache.UserCacheAdapter;
import com.ebanma.cloud.demo.adapter.cache.model.UserCacheDTO;
import com.ebanma.cloud.demo.adapter.integration.UserAdapter;
import com.ebanma.cloud.demo.adapter.integration.model.UserDTO;
import com.ebanma.cloud.demo.domain.entity.Order;
import com.ebanma.cloud.demo.domain.repository.OrderRepository;
import com.ebanma.cloud.demo.testdata.OrderTestData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderApplicationServiceTest {

    private OrderApplicationService orderApplicationService;

    @Mock
    private UserCacheAdapter userCacheAdapter;
    @Mock
    private UserAdapter userAdapter;
    @Mock
    private OrderRepository orderRepository;
    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        orderApplicationService = new OrderApplicationService(userCacheAdapter, userAdapter, orderRepository);
    }

    @Test
    public void should_save_order_when_create_order_given_user_cache_existed() {
        Order order = OrderTestData.genOrder();
        order.getMember().setName(null);
        Long userId = order.getMember().getId();
        UserCacheDTO userCacheDTO = new UserCacheDTO();
        userCacheDTO.setName("userName");

        when(userCacheAdapter.getUserById(userId)).thenReturn(userCacheDTO);

        orderApplicationService.create(order);

        verify(orderRepository).save(orderArgumentCaptor.capture());
        Order saved = orderArgumentCaptor.getValue();
        Assertions.assertThat(saved.getMember().getName()).isEqualTo("userName");
        Assertions.assertThat(saved.getMember().getId()).isEqualTo(userId);
    }

    @Test
    public void should_save_order_and_cache_user_when_create_order_given_no_user_cache() {
        Order order = OrderTestData.genOrder();
        order.getMember().setName(null);
        Long userId = order.getMember().getId();

        when(userCacheAdapter.getUserById(userId)).thenReturn(null);
//        UserDTO userDTO = new UserDTO();

    }
}