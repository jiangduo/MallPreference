package com.totoro.mall.utils;

import com.totoro.mall.model.entity.system.SysUser;

public class AuthContextUtil {
    //创建threadLocal对象
    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();
    //添加数据
    public static void set(SysUser sysUser){
        THREAD_LOCAL.set(sysUser);
    }
    //获取数据
    public static SysUser get(){
        return THREAD_LOCAL.get();
    }
    //删除数据
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
