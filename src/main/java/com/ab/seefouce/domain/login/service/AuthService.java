package com.ab.seefouce.domain.login.service;

import com.ab.seefouce.domain.login.model.vo.LoginVO;

public interface AuthService {
    LoginVO loginInWeChat(String code);
}
