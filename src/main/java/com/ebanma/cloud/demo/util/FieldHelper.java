package com.ebanma.cloud.demo.util;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

/**
 * 设置静态常量字段值
 *
 * 有时候，我们需要对静态常量对象进行模拟，然后去验证是否执行了对应分支下的方法。
 * 比如：需要模拟Lombok的@Slf4j生成的log静态常量。但是，Whitebox.setInternalState方法和@InjectMocks注解并不支持设置静态常量，
 * 需要自己实现一个设置静态常量的方法：
 */
public final class FieldHelper {

    /*
     * 注意：经过测试，该方法对于int、Integer等基础类型并不生效，应该是编译器常量优化导致。
     */
    public static void setStaticFinalField(Class< ?> clazz, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        FieldUtils.removeFinalModifier(field);
        FieldUtils.writeStaticField(field, fieldValue, true);
    }
}