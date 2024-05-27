package com.ab.seefouce.domain.user.resposity;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author cattleyuan
 * @date 2024/5/26
 */
@Component
public class RedisResposity{
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public<T> T getInString(String key,Class<T> tClass){
        T entity=null;
        String jsonbody = stringRedisTemplate.opsForValue().get(key);

        if (!jsonbody.isBlank()){
            JSON.parseObject(jsonbody,tClass);
        }

        return entity;
    }

    public boolean judgeInSet(String key,String value){
        return stringRedisTemplate.opsForSet().isMember(key,value);
    }

    public void addToSet(String key, String ...value){
        stringRedisTemplate.opsForSet().add(key,value);
    }

    public void removeFromSet(String key, String ...value){
        stringRedisTemplate.opsForSet().remove(key,value);
    }
}
