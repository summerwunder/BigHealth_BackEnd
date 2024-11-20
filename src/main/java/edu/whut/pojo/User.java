package edu.whut.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Object gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 积分
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 是否会员
     */
    @TableField(value = "is_member")
    private Integer isMember;

    /**
     * 是否推荐官
     */
    @TableField(value = "is_recommender")
    private Integer isRecommender;

    /**
     * 地址信息
     */
    @TableField(value = "address")
    private String address;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "id_card")
    private String idCard;
    @Version
    private Integer version; // 乐观锁版本号

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}