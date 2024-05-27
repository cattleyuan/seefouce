package com.ab.seefouce.domain.follow.service.impl;

import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.common.enums.GlobalServiceStatusCode;
import com.ab.seefouce.exception.GlobalServiceException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ab.seefouce.domain.follow.model.entity.Follow;
import com.ab.seefouce.domain.follow.service.FollowService;
import com.ab.seefouce.domain.follow.mapper.FollowMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author cattleyuan
* @description 针对表【follow】的数据库操作Service实现
* @createDate 2024-05-26 15:32:59
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
    implements FollowService{

    @Override
    public void followUp(Long userId) {
        //当前用户为粉丝
        Long follower = UserContextHolder.get().getUserId();
        //查询当前是否存在关注记录
        Follow record = lambdaQuery().eq(Follow::getUserId, userId).eq(Follow::getFollower, follower).one();

        //互斥锁解决线程安全问题
        synchronized (userId.toString().intern()) {
            if (Objects.nonNull(record)) {
                if (record.getStatus().equals(FOLLOW_SUCCESS_STATUS)) {
                    //已经关注过了
                    throw new GlobalServiceException("请勿重复关注", GlobalServiceStatusCode.USER_FAIL_FOLLOW);
                }
                //记录不为空则更新记录
                lambdaUpdate().eq(Follow::getUserId, userId).eq(Follow::getFollower, follower).set(Follow::getStatus, FOLLOW_SUCCESS_STATUS).update();
                return;
            }
            //保存关注信息
            saveFollow(userId, follower);
        }


    }

    private void saveFollow(Long userId, Long follower) {
        //构建follow实体
        Follow follow = new Follow();
        follow.setUserId(userId).setFollower(follower).setStatus(1);
        this.save(follow);
    }

    @Override
    public void followDown(Long userId) {
        //当前用户为粉丝
        Long follower = UserContextHolder.get().getUserId();
        //查询当前是否存在关注记录
        Follow record = lambdaQuery().eq(Follow::getUserId, userId).eq(Follow::getFollower, follower).one();

        if(!Objects.nonNull(record)){
            //关注记录为空，抛出异常
            throw new GlobalServiceException("尚未关注此用户或此用户不存在",GlobalServiceStatusCode.USER_CANCLE_FAIL_FOLLOW);
        }

        if(record.getStatus()==0){
            throw new GlobalServiceException("已取消关注该用户",GlobalServiceStatusCode.USER_CANCLE_FAIL_FOLLOW);
        }
        //记录不为空则更新记录
        lambdaUpdate().eq(Follow::getUserId, userId).eq(Follow::getFollower, follower).set(Follow::getStatus,FOLLOW_CANCLE_STATUS).update();

    }
}




