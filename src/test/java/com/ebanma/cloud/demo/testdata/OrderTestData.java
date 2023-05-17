package com.ebanma.cloud.demo.testdata;
import java.math.BigDecimal;
import com.ebanma.cloud.demo.domain.valueObject.User;

import com.ebanma.cloud.demo.domain.entity.Order;

public class OrderTestData {

    public static Order genOrder() {
        Order order = new Order();
        order.setOrderNo("orderNo");
        order.setMember(genMember());
        order.setSupplierId(1L);
        order.setProductId(1L);
        order.setProductCount(100L);
        order.setProductAmountTotal(new BigDecimal("100"));
        order.setOrderAmountTotal(new BigDecimal("100"));
        order.setAddress("address");
        return order;
    }

    public static User genMember() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        return user;
    }
}