package com.ebanma.cloud.demo.domain.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultIdGenerator implements IdGenerator {
    @Override
    public Long next() {
        return null;
    }
}