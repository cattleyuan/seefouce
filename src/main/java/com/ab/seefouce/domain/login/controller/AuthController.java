package com.ab.seefouce.domain.login.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.domain.login.model.vo.LoginVO;
import com.ab.seefouce.domain.login.service.AuthService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cattleYuan
 * @date 2024/5/22
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Validated
public class AuthController {

    private final AuthService authService;

    /*
     * @description: 登录兼注册接口
     * @author: cattleyuan
     * @date: 2024/5/23 22:39
     * @param: code 请求码
     * @return: SystemJsonResponse
     **/
    @GetMapping("/login")
    public SystemJsonResponse login(@NotNull(message = "code不能为空") String code){
        LoginVO loginInfo = authService.loginInWeChat(code);
        return SystemJsonResponse.SYSTEM_SUCCESS(loginInfo);
    }

}
