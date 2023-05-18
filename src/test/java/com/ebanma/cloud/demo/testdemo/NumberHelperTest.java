package com.ebanma.cloud.demo.testdemo;

import org.junit.Assert;
import org.junit.Test;

/**
 * 一个最简单的单元测试
 */
public class NumberHelperTest {
    @Test
    public void testIsPositiveInt() {
        Assert.assertFalse("返回值不为假", NumberHelper.isPositive(-1));
        Assert.assertFalse("返回值不为假", NumberHelper.isPositive(0));
        Assert.assertTrue("返回值不为真", NumberHelper.isPositive(1));
        Assert.assertFalse("返回值不为假", NumberHelper.isPositive((Integer) null));
        Assert.assertFalse("返回值不为假", NumberHelper.isPositive(Integer.valueOf("-1")));
        Assert.assertFalse("返回值不为假", NumberHelper.isPositive(Integer.valueOf("0")));
        Assert.assertTrue("返回值不为真", NumberHelper.isPositive(Integer.valueOf("1")));
    }
}