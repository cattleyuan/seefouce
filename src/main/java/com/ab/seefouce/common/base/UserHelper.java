package com.ab.seefouce.common.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Getter
@Setter
public class UserHelper {
    //用户id
    Long userId;

    //用户openid
    String openid;

    //用户昵称
    String nickName;
}
