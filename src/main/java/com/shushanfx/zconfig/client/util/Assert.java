package com.shushanfx.zconfig.client.util;

/**
 * Created by shushanfx on 2017/6/12.
 */
public final class Assert {
    public static void notNull(Object obj, String message){
        if(obj == null){
            throw new NullPointerException(message);
        }
    }

    public static void notNull(Object obj){
        notNull(obj, "Cannot be null.");
    }

    public static void assertTrue(boolean expression, String message){
        if(expression == false){
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertTrue(boolean expression){
        assertTrue(expression, "Expression must be true.");
    }
}
