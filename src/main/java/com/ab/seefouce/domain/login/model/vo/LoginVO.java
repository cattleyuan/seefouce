package com.ab.seefouce.domain.login.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author cattleyuan
 * @date 2024/5/22
 */
@Data
@Accessors(chain = true)
public class LoginVO implements Serializable {
    //用户id
    private Long id;
    //用户唯一标识
    private String openid;
    //鉴权token
    private String wxtoken;
}
