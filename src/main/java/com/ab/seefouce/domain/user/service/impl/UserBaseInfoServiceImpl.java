package com.ab.seefouce.domain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.common.constants.RedisCacheConstant;
import com.ab.seefouce.domain.user.mapper.UserBaseInfoMapper;
import com.ab.seefouce.domain.user.model.dto.UserBaseInfoDTO;
import com.ab.seefouce.domain.user.model.entity.UserBaseInfo;

import com.ab.seefouce.domain.user.resposity.RedisResposity;
import com.ab.seefouce.domain.user.service.UserBaseInfoService;

import com.ab.seefouce.exception.GlobalServiceException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* @author cattleyuan
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-05-23 22:30:01
*/
@Service
@RequiredArgsConstructor
public class UserBaseInfoServiceImpl extends ServiceImpl<UserBaseInfoMapper, UserBaseInfo>
    implements UserBaseInfoService {


    @Override
    public UserBaseInfoDTO getBaseUserInfo() {
        Long userId = UserContextHolder.get().getUserId();
        //查询用户基本信息
        UserBaseInfo userBaseInfo = lambdaQuery().eq(userId != null, UserBaseInfo::getUserId, userId).one();

        Optional.ofNullable(userBaseInfo).orElseThrow(()->new GlobalServiceException("用户尚未注册"));
        //构建返回条件
        UserBaseInfoDTO userBaseInfoDTO = new UserBaseInfoDTO();
        BeanUtil.copyProperties(userBaseInfo,userBaseInfoDTO);

        return userBaseInfoDTO;
    }




}




