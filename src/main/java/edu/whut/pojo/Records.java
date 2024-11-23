package edu.whut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 预约记录表
 * @TableName records
 */
@TableName(value ="records")
@Data
public class Records implements Serializable {
    /**
     * 预约记录主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 体检人 ID，关联 check_user 表
     */
    @TableField(value = "check_user_id")
    private Long checkUserId;

    /**
     * 商品 ID，关联 product 表
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 预约时间
     */
    @TableField(value = "appointment_time")
    private Date appointmentTime;

    /**
     * 预约状态，如已预约、已取消
     */
    @TableField(value = "status")
    private String status;

    /**
     * 记录创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 记录更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除标志（0: 未删除, 1: 已删除)
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 乐观锁版本号
     */
    @TableField(value = "version")
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}