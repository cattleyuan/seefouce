package com.ab.seefouce.domain.user.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
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
public class UserInfoDTO implements Serializable {
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
    private Integer sex;

    /**
     * 身高
     */
    private Double height;

    /**
     * 健身格言
     */
    private String fitnessMotto;

    /**
     * 能量币
     */
    private Long energyCoins;

    /**
     *加入时间
    */

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
