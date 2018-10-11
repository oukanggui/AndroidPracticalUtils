package com.baymax.utilslib;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 Json操作工具类，利用Gson通过泛型实现任意对象或列表对象与Json数据的转换，支持功能如下：
 * 1、将任意对象或列表转为Json字符串
 * 2、将Json字符串转换为任意对象或列表(通过泛型)
 */

public class JsonUtil {
    private static Gson sGson = new Gson();

    /**
     * 任意对象-->>Json字符串
     *
     * @param object 待转换对象
     * @return 对象转化后的Json字符串
     */
    private static String toJson(Object object) {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson.toJson(object);
    }

    /**
     * Json字符串-->>对象
     *
     * @param json   待转换的Json字符串
     * @param tClass 转换结果对象的Class
     * @return 返回转换后的对象
     */
    private static <T> T parseJson(String json, Class<T> tClass) {
        if (sGson == null) {
            sGson = new Gson();
        }
        try {
            return sGson.fromJson(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json字符串-->>List列表对象
     *
     * @param json     待转换的Json字符串
     * @param listType 转换结果List的Type
     * @return 返回转换后的List列表对象
     */
    private static <T> List<T> parseJson(String json, Type listType) {
        if (listType == null) {
            return null;
        }
        if (sGson == null) {
            sGson = new Gson();
        }
        try {
            return sGson.fromJson(json, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
