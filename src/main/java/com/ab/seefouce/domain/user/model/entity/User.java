package com.ab.seefouce.domain.user.model.entity;

import com.ab.seefouce.common.base.BaseIncrIDEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Accessors(chain = true)
public class User extends BaseIncrIDEntity implements Serializable {

    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 用户名称
     */
    private String nickName;

    /**
     * 用户唯一标识
     */
    private String openid;

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



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}