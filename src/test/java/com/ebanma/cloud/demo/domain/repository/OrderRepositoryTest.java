package com.ebanma.cloud.demo.domain.repository;

import com.ebanma.cloud.demo.data.dao.OrderDao;
import com.ebanma.cloud.demo.data.model.OrderDO;
import com.ebanma.cloud.demo.domain.entity.Order;
import com.ebanma.cloud.demo.testdata.OrderTestData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderRepositoryTest {

    private OrderRepository orderRepository;
    @Mock
    private OrderDao orderDao;
    @Captor
    private ArgumentCaptor<OrderDO> orderDOArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        orderRepository = new OrderRepository(orderDao);
    }

    @Test
    public void should_save_serialized_order_data_object_when_save_given_order_domain_object() {
        Order order = OrderTestData.genOrder();

        orderRepository.save(order);

        verify(orderDao).insert(orderDOArgumentCaptor.capture());
        OrderDO saved = orderDOArgumentCaptor.getValue();
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getOrderNo()).isEqualTo("orderNo");
        Assertions.assertThat(saved.getMemberId()).isEqualTo(1L);
        Assertions.assertThat(saved.getMemberName()).isEqualTo("name");
        Assertions.assertThat(saved.getSupplierId()).isEqualTo(1L);
        Assertions.assertThat(saved.getProductId()).isEqualTo(1L);
        Assertions.assertThat(saved.getProductCount()).isEqualTo(100L);
        Assertions.assertThat(saved.getProductAmountTotal()).isEqualTo(new BigDecimal(100));
        Assertions.assertThat(saved.getOrderAmountTotal()).isEqualTo(new BigDecimal(100));
        Assertions.assertThat(saved.getAddress()).isEqualTo("address");
        Assertions.assertThat(saved.getCreateTime()).isNotNull();
    }
}