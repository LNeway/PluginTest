package com.devtom.plugintest;

import java.lang.reflect.Field;

/**
 * Created by tomliu on 2017/9/3.
 */

public class ReflectUtils {

    public static Object readField(Object object, String fieldName) {
        Class clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeField(Object object, String fieldName, Object value) {
        Class clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
