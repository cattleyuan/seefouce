package com.ab.seefouce.domain.follow.model.entity;

import com.ab.seefouce.common.base.BaseIncrIDEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName follow
 */
@TableName(value ="follow")
@Data
@Accessors(chain = true)
public class Follow extends BaseIncrIDEntity implements Serializable {

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long follower;

    /**
     * 
     */
    private Integer status;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}