package edu.whut.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 轮播图管理表
 * @TableName banner
 */
@TableName(value ="banner")
@Data
public class Banner implements Serializable {
    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图片 URL
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 轮播图位置
     */
    @TableField(value = "position")
    private Object position;

    /**
     * 排序，数字越大越靠后
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 记录创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 记录更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除标志（0: 未删除, 1: 已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}