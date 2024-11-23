package edu.whut.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单详情表
 * @TableName order_details
 */
@TableName(value ="order_details")
@Data
public class OrderDetails implements Serializable {
    /**
     * 订单详情主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单 ID，关联订单表
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 用户 ID，关联用户表
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品名称
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 订单状态，如待支付、已支付
     */
    @TableField(value = "status")
    private String status;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private Date orderTime;

    /**
     * 订单总价
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    /**
     * 逻辑删除标志（0: 未删除，1: 已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(value = "is_used")
    private Integer isUsed;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}