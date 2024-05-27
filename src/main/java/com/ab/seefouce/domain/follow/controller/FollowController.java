package com.ab.seefouce.domain.follow.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.domain.follow.service.FollowService;
import com.ab.seefouce.domain.photo.model.dto.PhotoDTO;
import com.ab.seefouce.domain.photo.service.PhotoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cattleYuan
 * @date 2024/5/26
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
@Validated
public class FollowController {
    private final FollowService followService;
    /**
     * @description: 关注
     * @author: cattleyuan
     * @date: 2024/5/26 15:00
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @PutMapping("/")
    public SystemJsonResponse followUp(@NotNull(message = "用户id不能为空") Long userId){
            followService.followUp(userId);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }
    /**
     * @description: 取消关注
     * @author: cattleyuan
     * @date: 2024/5/26 15:00
     * @param: userId 用户id
     * @return: SystemJsonResponse
     **/
    @DeleteMapping("/del")
    public SystemJsonResponse followDown(@NotNull(message = "用户id不能为空") Long userId){
           followService.followDown(userId);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }
}
