package com.ab.seefouce.domain.user.model.entity;

import com.ab.seefouce.common.base.BaseIncrIDEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user_base_info
 */
@TableName(value = "user_info")
@Data
@Accessors(chain = true)
public class UserInfo extends BaseIncrIDEntity implements Serializable {

    /**
     * 自增id
     */
    private long id;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 未知
     */
    private int beRecommendedNumber;

    /**
     * 曝光度
     */
    private int exposureNumber;

    /**
     * 关注我的人数
     */
    private int followMeNumber;

    /**
     * 运动目标
     */
    private String gymGoal;

    /**
     * 是否连接设备
     */
    private boolean hasConnected;

    /**
     * 我关注的人数
     */
    private int iFollowNumber;

    /**
     * 能量币数
     */
    private long iconsNumber;

    /**
     * 收获点赞数
     */
    private int likeNumber;

    /**
     * 注册时间
     */
    private LocalDateTime registrationTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}