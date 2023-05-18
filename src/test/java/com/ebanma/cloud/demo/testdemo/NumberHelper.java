package com.ebanma.cloud.demo.testdemo;

import java.util.Objects;

/**
 * 一个最简单的单元测试
 */
public final class NumberHelper {
    public static final int INT_ZERO = 0;
    public static boolean isPositive(Integer value) {
        return Objects.nonNull(value) && Integer.compare(value, INT_ZERO) > 0;
    }
}