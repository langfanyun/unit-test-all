package com.ebanma.cloud.demo.data.dao;

import com.ebanma.cloud.demo.data.model.OrderDO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@MybatisTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void should_select_order_by_id() {
        OrderDO orderDO = this.orderDao.selectOrderById(1L);

        Assertions.assertThat(orderDO).isNotNull();
        Assertions.assertThat(orderDO.getId()).isEqualTo(1L);
        Assertions.assertThat(orderDO.getOrderNo()).isEqualTo("order_no");
        Assertions.assertThat(orderDO.getMemberId()).isEqualTo(100);
        Assertions.assertThat(orderDO.getSupplierId()).isEqualTo(100);
        Assertions.assertThat(orderDO.getProductId()).isEqualTo(100);
        Assertions.assertThat(orderDO.getProductCount()).isEqualTo(100);
        Assertions.assertThat(orderDO.getProductAmountTotal()).isEqualTo(new BigDecimal("100.00"));
        Assertions.assertThat(orderDO.getOrderAmountTotal()).isEqualTo(new BigDecimal("100.00"));
        Assertions.assertThat(orderDO.getAddress()).isEqualTo("address");
        Assertions.assertThat(orderDO.getCreateTime()).isNotNull();
    }
}