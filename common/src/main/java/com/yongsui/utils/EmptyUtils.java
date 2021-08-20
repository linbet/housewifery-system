package com.yongsui.utils;

import java.util.*;

/**
 * @Description: 判空工具类
 * @Author: tengmingfa
 * @Date: 2021年08月19日
 */
public class EmptyUtils {

    /**
     * 判断集合是否为空
     * @param obj
     * @return
     */
    public static Boolean isEmpty(Collection<?> obj){
        return (obj == null || obj.isEmpty());
    }

    /**
     * 判断字符串是否为空
     * @param obj
     * @return
     */
    public static Boolean isEmpty(String obj){
        System.out.println(1);
        return (obj == null || "".equals(obj));
    }

    /**
     * 判断数组是否为空
     * @param obj
     * @return
     */
    public static Boolean isEmpty(Object[] obj){
        return (obj == null || obj.length == 0);
    }

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static Boolean isEmpty(Object obj){
        return (obj == null || "".equals(obj));
    }

    /**
     * 判断Map是否为空
     * @param obj
     * @return
     */
    public static Boolean isEmpty(Map<?,?> obj){
        return (obj == null || obj.isEmpty());
    }
}
