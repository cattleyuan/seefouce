package com.ab.seefouce.domain.user.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserBaseInfoDTO implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 用户名称
     */
    private String nickName;


    /**
     * 性别
     */
    private Integer gender;

    /**
     * 身高
     */
    private Double height;

    /**
     * 健身格言
     */
    private String motto;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
