package com.ebanma.cloud.demo.data.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Redis 辅助类
 */
public final class RedisHelper {

    private RedisHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * 解析值
     */
    public static <V> V parseValue(String text, Class<V> clazz) {
        if (Objects.equals(clazz, String.class)) {
            return clazz.cast(text);
        }
        return JSON.parseObject(text, clazz);
    }

    /**
     * 解析值列表
     */
    public static <V> List<V> parseValues(List<String> textList, Class<V> clazz) {
        // 判断文本列表
        if (CollectionUtils.isEmpty(textList)) {
            return Collections.emptyList();
        }

        // 解析值列表
        List<V> valueList = new ArrayList<>(textList.size());
        for (String text : textList) {
            valueList.add(parseValue(text, clazz));
        }

        // 返回值列表
        return valueList;
    }
}