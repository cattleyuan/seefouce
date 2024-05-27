package com.ab.seefouce.domain.user.service;

import com.ab.seefouce.domain.user.model.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserInfoService extends IService<UserInfo> {

    void thumbsUp(Long thumbsId);

    void thumbsDown(Long userId);
}
