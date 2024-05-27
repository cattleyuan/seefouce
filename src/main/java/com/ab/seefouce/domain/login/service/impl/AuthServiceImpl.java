package com.ab.seefouce.domain.login.service.impl;

import com.ab.seefouce.common.enums.GlobalServiceStatusCode;
import com.ab.seefouce.common.propertities.JwtProperties;
import com.ab.seefouce.common.propertities.WeChatProperties;
import com.ab.seefouce.domain.login.model.vo.LoginVO;
import com.ab.seefouce.domain.login.service.AuthService;
import com.ab.seefouce.domain.user.model.entity.UserBaseInfo;
import com.ab.seefouce.domain.user.model.entity.UserInfo;
import com.ab.seefouce.domain.user.service.UserBaseInfoService;
import com.ab.seefouce.domain.user.service.UserInfoService;
import com.ab.seefouce.exception.GlobalServiceException;
import com.ab.seefouce.utils.HttpClientUtil;
import com.ab.seefouce.utils.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author cattleyuan
 * @date 2024/5/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    public static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String NICKNAME_PERFIX = "jli";

    private final WeChatProperties weChatProperties;
    private final UserBaseInfoService userBaseInfoService;
    private final UserInfoService userInfoService;
    private final JwtProperties jwtProperties;


    @Override
    public LoginVO loginInWeChat(String code) {
        //发起请求获取openid
        String openid=requestForOpenid(code);
        Optional.ofNullable(openid).orElseThrow(()->new GlobalServiceException("无效的请求code",GlobalServiceStatusCode.USER_ACCOUNT_DISABLE));
        //根据openid查找用户,来决定是否注册
        UserInfo userInfo  = LoginOrRegisterWithUser(openid);
        //生成token
        String token= createWxToken(userInfo,openid);

        return BuildLoginVO(openid,token, userInfo.getId());
    }

    @Transactional
    public UserInfo LoginOrRegisterWithUser(String openid) {
        UserInfo userInfo = userInfoService.lambdaQuery().eq(UserInfo::getOpenid, openid).one();
        if(userInfo !=null){
            return userInfo;
        }
        //注册新用户(插入用户数据两张表)
        UserBaseInfo userBaseInfo = new UserBaseInfo().setNickName(getRandName());
        userInfo =new UserInfo().setOpenid(openid);

        boolean uSuccess=userInfoService.save(userInfo);
        boolean bSuccess=userBaseInfoService.save(userBaseInfo.setUserId(userInfo.getId()));

        if(!uSuccess&&bSuccess){
            String message=String.format("注册新用户->昵称：%s,openid:%s失败", userBaseInfo.getNickName(), userInfo.getOpenid());
            throw new GlobalServiceException(message,GlobalServiceStatusCode.USER_ACCOUNT_REGISTER_ERROR);
        }
        return userInfo;
    }
    //获取随机名字
    private String getRandName() {
        return  NICKNAME_PERFIX+UUID.randomUUID().toString().substring(0,16);
    }

    private LoginVO BuildLoginVO(String openid, String token, Long userId) {
        return new LoginVO().setId(userId).setWxtoken(token).setOpenid(openid);
    }

    //生成token
    private String createWxToken(UserInfo userInfo, String openid) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userInfo.getId());
        claims.put("openid",openid);
        return JwtUtil.createJWT(JwtUtil.generalKey(jwtProperties.getSecretKey()),jwtProperties.getTtl(),claims);
    }

    private String requestForOpenid(String code) {
        Map<String, String> loginMap = BuildLoginMap(code);
        //获取响应json
        String json = HttpClientUtil.doGet(LOGIN_URL, loginMap);
        //转换为响应体
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = (String)jsonObject.get("openid");

        return openid;
    }

    //准备请求信息
    private Map<String, String> BuildLoginMap(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        return map;
    }
}
