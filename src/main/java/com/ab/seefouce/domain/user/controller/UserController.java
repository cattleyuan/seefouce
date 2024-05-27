package com.ab.seefouce.domain.user.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.domain.user.model.dto.UserBaseInfoDTO;
import com.ab.seefouce.domain.user.service.UserBaseInfoService;
import com.ab.seefouce.domain.user.service.UserInfoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cattleYuan
 * @date 2024/5/22
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    private final UserBaseInfoService userBaseInfoService;
    private final UserInfoService userInfoService;
    /**
     * @description: 查询用户非敏感信息
     * @author: cattleyuan
     * @date: 2024/5/24 0:39
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @PostMapping("/")
    public SystemJsonResponse queryUserBaseInfo(){
        UserBaseInfoDTO userBaseInfoDTO = userBaseInfoService.getBaseUserInfo();
        return SystemJsonResponse.SYSTEM_SUCCESS(userBaseInfoDTO);
    }

    /**
     * @description: 点赞
     * @author: cattleyuan
     * @date: 2024/5/26 15:00
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @PutMapping("/thumbs")
    public SystemJsonResponse thumbsUp(@NotNull(message = "用户id不能为空") Long userId){
        userInfoService.thumbsUp(userId);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }

    /**
     * @description: 取消点赞
     * @author: cattleyuan
     * @date: 2024/5/26 15:00
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @DeleteMapping("/thumbs")
    public SystemJsonResponse thumbsDown(@NotNull(message = "用户id不能为空") Long userId){
        userInfoService.thumbsDown(userId);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }

}
