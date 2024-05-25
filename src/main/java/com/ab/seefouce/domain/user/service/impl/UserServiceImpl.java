package com.ab.seefouce.domain.user.service.impl;

import com.ab.seefouce.domain.user.mapper.UserMapper;
import com.ab.seefouce.domain.user.model.dto.UserInfoDTO;
import com.ab.seefouce.domain.user.model.entity.User;
import com.ab.seefouce.domain.user.service.UserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 86150
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-05-23 22:30:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public UserInfoDTO getUserBaseInfo(Long userId) {
        return null;
    }
}




