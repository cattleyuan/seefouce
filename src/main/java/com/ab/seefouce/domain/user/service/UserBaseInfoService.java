package com.ab.seefouce.domain.user.service;


import com.ab.seefouce.domain.user.model.dto.UserBaseInfoDTO;
import com.ab.seefouce.domain.user.model.entity.UserBaseInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86150
* @description 针对表【user】的数据库操作Service
* @createDate 2024-05-23 22:30:01
*/
public interface UserBaseInfoService extends IService<UserBaseInfo> {

    UserBaseInfoDTO getBaseUserInfo();

}
