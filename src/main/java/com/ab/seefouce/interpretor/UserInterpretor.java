package com.ab.seefouce.interpretor;


import cn.hutool.core.util.StrUtil;

import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.common.base.UserHelper;
import com.ab.seefouce.common.constants.Passable;
import com.ab.seefouce.common.enums.GlobalServiceStatusCode;
import com.ab.seefouce.common.propertities.JwtProperties;
import com.ab.seefouce.exception.GlobalServiceException;

import com.ab.seefouce.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;


/**
 * @author cattleYuan
 * @date 2024/1/18
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class UserInterpretor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    public static final String USER_ID = "userId";
    public static final String OPENID = "openid";
    public static final String NICK_NAME = "nickName";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //可以解决拦截器跨域问题
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //判断是否该方法是否无需token
        if (judgePassedWithNoneToken((HandlerMethod)handler)) {
            return true;
        }

        String token = request.getHeader(jwtProperties.getTokenName());
        //从请求头中获取token
        if (StrUtil.isEmpty(token)) {
            throw new GlobalServiceException("用户未登录,token为空", GlobalServiceStatusCode.USER_NOT_LOGIN);
        }
        //通过明文钥匙生成密钥
        SecretKey secretKey = JwtUtil.generalKey(jwtProperties.getSecretKey());

        Claims claims = JwtUtil.parseJWT(secretKey, token);
        //通过线程局部变量设置当前线程用户信息
        setCurrentUserInfo(claims);
        //判断token是否即将过期
        if (JwtUtil.judgeApproachExpiration(token, secretKey)) {
            refreshToken(response, secretKey, claims);
        }
        return true;
    }

    private boolean judgePassedWithNoneToken(HandlerMethod handlerMethod) {
        Passable annotation = handlerMethod.getMethodAnnotation(Passable.class);

        if(annotation!=null){
            return true;
        }
        return false;
    }

    private void setCurrentUserInfo(Claims claims) {
        UserHelper userHelper = new UserHelper();

        userHelper.setUserId(Long.valueOf(claims.get(USER_ID).toString()));
        userHelper.setOpenid((String) claims.get(OPENID));
        userHelper.setNickName((String) claims.get(NICK_NAME));

        UserContextHolder.set(userHelper);
    }

    private void refreshToken(HttpServletResponse response, SecretKey secretKey, Claims claims) {
        long ttl = jwtProperties.getTtl();
        String refreshToken = JwtUtil.createJWT(secretKey, ttl, claims);
        //刷新token,通过请求头返回前端
        response.setHeader(jwtProperties.getTokenName(), refreshToken);
        log.info("无感刷新token:{}", refreshToken);
    }


}
