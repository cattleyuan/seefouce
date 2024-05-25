package com.ab.seefouce.common.base;

import com.ab.seefouce.exception.GlobalServiceException;

import java.nio.file.OpenOption;
import java.util.Optional;

/**
 * @author cattleyuan
 * @date 2024/5/24
 * @descrption 用户线程局部变量（饿汉式加载threadLocal）
 */
public class UserContextHolder {
    private static ThreadLocal<UserHelper> threadLocal=new ThreadLocal();

    public static void set(UserHelper userHelper){
        Optional.ofNullable(userHelper).orElseThrow(()->new GlobalServiceException("无效的线程局部变量获取设置"));
        threadLocal.set(userHelper);
    }
    public static UserHelper get(){
        UserHelper userHelper = threadLocal.get();
        Optional.ofNullable(userHelper).orElseThrow(()->new GlobalServiceException("无效的线程局部变量获取"));
        return userHelper;
    }

    public static void remove(){
        threadLocal.remove();
    }
}
