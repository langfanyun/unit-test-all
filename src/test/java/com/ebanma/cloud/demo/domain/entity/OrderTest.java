package com.ebanma.cloud.demo.domain.entity;

import com.ebanma.cloud.demo.domain.service.OrderService;
import com.ebanma.cloud.demo.domain.service.specification.OrderSpecification;
import com.ebanma.cloud.demo.domain.service.strategy.ArchiveOrderStrategy;
import com.ebanma.cloud.demo.domain.service.strategy.EffectiveOrderStrategy;
import com.ebanma.cloud.demo.domain.service.strategy.IllegalOrderStrategy;
import com.ebanma.cloud.demo.domain.service.strategy.NormalOrderStrategy;
import org.junit.Before;

import java.util.Arrays;

public class OrderTest {

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService(Arrays.asList(new IllegalOrderStrategy(), new NormalOrderStrategy(),
                new EffectiveOrderStrategy(), new ArchiveOrderStrategy()), new OrderSpecification());
    }



}