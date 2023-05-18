package com.ebanma.cloud.demo.testdemo;

import org.junit.Assert;
import org.junit.Test;

/**
 * 一个无依赖的单元测试
 */
public class RectangleTest {
    @Test
    public void testGetArea() {
        // 定义测试对象
        Rectangle rectangle = new Rectangle(3.0D, 4.0D);
        // 调用测试方法
        Assert.assertEquals("面积不一致", 12.0D, rectangle.getArea(), 1E-6D);
    }
}