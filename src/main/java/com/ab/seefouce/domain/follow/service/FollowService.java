package com.ab.seefouce.domain.follow.service;

import com.ab.seefouce.domain.follow.model.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cattleyuan
* @description 针对表【follow】的数据库操作Service
* @createDate 2024-05-26 15:32:59
*/
public interface FollowService extends IService<Follow> {
    //关注
    public static final Integer FOLLOW_SUCCESS_STATUS=1;
    //取消关注
    public static final Integer FOLLOW_CANCLE_STATUS=0;

    void followUp(Long userId);

    void followDown(Long userId);
}
