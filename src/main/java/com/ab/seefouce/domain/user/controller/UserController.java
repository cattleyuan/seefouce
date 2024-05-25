package com.ab.seefouce.domain.user.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.common.constants.Passable;
import com.ab.seefouce.domain.fitgoal.model.dto.FitnessGoalDTO;
import com.ab.seefouce.domain.user.model.dto.UserInfoDTO;
import com.ab.seefouce.domain.user.service.UserService;
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
    private final UserService userService;
    /*
     * @description: 查询用户非敏感信息
     * @author: cattleyuan
     * @date: 2024/5/24 0:39
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @Passable
    @PostMapping("/{userId}")
    public SystemJsonResponse queryUserInfo(@PathVariable @NotNull(message = "用户id不能为空") Long userId){
        UserInfoDTO userInfoDTO= userService.getUserBaseInfo(userId);
        return SystemJsonResponse.SYSTEM_SUCCESS(userInfoDTO);
    }

    @PostMapping("/")
    public SystemJsonResponse addFitnessGoals(@Validated @NotNull(message = "请求参数不能为空") FitnessGoalDTO fitnessGoalDTO){

        return SystemJsonResponse.SYSTEM_SUCCESS();
    }
}
