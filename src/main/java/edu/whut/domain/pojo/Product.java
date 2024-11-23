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
 * 商品表
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "type")
    private String type;

    /**
     * 是否上架（0: 否, 1: 是）
     */
    @TableField(value = "is_listed")
    private Integer isListed;

    /**
     * 是否推荐（0: 否, 1: 是）
     */
    @TableField(value = "is_recommended")
    private Integer isRecommended;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 商品介绍
     */
    @TableField(value = "details")
    private String details;

    /**
     * 逻辑删除标志（0: 未删除, 1: 已删除）
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