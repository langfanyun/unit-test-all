package com.ebanma.cloud.demo.testdemo;

/**
 * 一个无依赖的单元测试
 */
public class Rectangle {
    private final double width;
    private final double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getArea() {
        return width * height;
    }
}
