package com.ebanma.cloud.demo.interactive.request;
import java.math.BigDecimal;
import java.util.Set;

import com.ebanma.cloud.demo.domain.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class OrderCreateRequestTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void should_return_order_entity_when_convert_from_order_create_request() {
        // given
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setOrderNo("orderNo");
        orderCreateRequest.setMemberId(1L);
        orderCreateRequest.setSupplierId(1L);
        orderCreateRequest.setProductId(1L);
        orderCreateRequest.setProductCount(100L);
        orderCreateRequest.setProductAmountTotal(new BigDecimal("200"));
        orderCreateRequest.setOrderAmountTotal(new BigDecimal("100"));
        orderCreateRequest.setAddress("address");

        // when
        Order order = orderCreateRequest.convert();

        // then
        Assertions.assertThat(order.getOrderNo()).isEqualTo("orderNo");
        Assertions.assertThat(order.getMember().getId()).isEqualTo(1L);
        Assertions.assertThat(order.getSupplierId()).isEqualTo(1L);
        Assertions.assertThat(order.getProductId()).isEqualTo(1L);
        Assertions.assertThat(order.getProductCount()).isEqualTo(100L);
        Assertions.assertThat(order.getProductAmountTotal()).isEqualTo(new BigDecimal("200"));
        Assertions.assertThat(order.getOrderAmountTotal()).isEqualTo(new BigDecimal("100"));
        Assertions.assertThat(order.getAddress()).isEqualTo("address");
    }

    @Test
    public void should_has_no_constraint_violation_when_validate_order_request_body() {
        // given
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setAddress("address");
        orderCreateRequest.setMemberId(1L);
        orderCreateRequest.setProductCount(100L);

        // when
        Set<ConstraintViolation<OrderCreateRequest>> constraintViolations = validator.validate(orderCreateRequest);

        // then
        Assertions.assertThat(constraintViolations).isEmpty();
    }

    @Test
    public void should_has_constraint_violation_when_validate_order_request_body() {
        // given
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setAddress("");
        orderCreateRequest.setMemberId(null);
        orderCreateRequest.setProductCount(100000L);

        // when
        Set<ConstraintViolation<OrderCreateRequest>> constraintViolations = validator.validate(orderCreateRequest);

        // then
        Assertions.assertThat(constraintViolations.size()).isEqualTo(3);
        this.assertConstraintViolation("address", "", "不能为空", constraintViolations);
        this.assertConstraintViolation("memberId", null, "不能为null", constraintViolations);
        this.assertConstraintViolation("productCount", 100000L, "需要在0和1000之间", constraintViolations);
    }

    private void assertConstraintViolation(String path, Object value, String message,
                                           Set<ConstraintViolation<OrderCreateRequest>> constraintViolations) {
        ConstraintViolation<OrderCreateRequest> constraintViolation = constraintViolations.stream()
                .filter(it -> it.getPropertyPath().toString().equals(path))
                .findFirst()
                .get();
        Assertions.assertThat(constraintViolation.getInvalidValue()).isEqualTo(value);
        Assertions.assertThat(constraintViolation.getMessage()).isEqualTo(message);
    }
}