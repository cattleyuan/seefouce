package com.ab.seefouce.domain.user.model.entity;

import com.ab.seefouce.common.base.BaseIncrIDEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * @TableName user_base_info
 */
@TableName(value ="user_base_info")
@Data
@Accessors(chain = true)
public class UserBaseInfo extends BaseIncrIDEntity implements Serializable {


    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 用户名称
     */
    private String nickName;


    /**
     * 用户id
     */
    private Long userId;


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