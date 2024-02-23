package com.example.springbootdemo.injector;

/**
 * @author gaosen
 * @since 2024/2/22 15:23
 */
import java.lang.reflect.Field;

public class ReflectionUtils {

    public static void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
