package com.ab.seefouce.domain.user.service.impl;

import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.common.constants.RedisCacheConstant;
import com.ab.seefouce.domain.user.mapper.UserBaseInfoMapper;
import com.ab.seefouce.domain.user.mapper.UserInfoMapper;
import com.ab.seefouce.domain.user.model.entity.UserInfo;
import com.ab.seefouce.domain.user.resposity.RedisResposity;
import com.ab.seefouce.domain.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author cattleyuan
 * @date 2024/5/26
 */
@Service
@RequiredArgsConstructor
public class UserInfoImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    private final RedisResposity redisResposity;

    @Override
    public void thumbsUp(Long userId) {

        String key= RedisCacheConstant.THUMBS_UP_CACHE_NAME+userId;
        String followId= UserContextHolder.get().getUserId().toString();

        //TODO 考虑加锁但非敏感业务
        //判断当前用户是否点赞过ThumbsId用户
        if(!redisResposity.judgeInSet(key,followId)){
            //TODO 考虑多线程异步更新数据库点赞数，或定时任务更新
            //执行点赞逻辑
            redisResposity.addToSet(key,followId);
        }

    }

    @Override
    public void thumbsDown(Long userId) {

        String key= RedisCacheConstant.THUMBS_UP_CACHE_NAME+userId;
        String followId= UserContextHolder.get().getUserId().toString();

        //TODO 考虑加锁但非敏感业务
        //判断当前用户是否点赞过ThumbsId用户
        if(redisResposity.judgeInSet(key,followId)){
            //取消点赞
            //TODO 考虑多线程异步更新数据库点赞数，或定时任务更新
            redisResposity.removeFromSet(key,followId);
        }
    }
}
