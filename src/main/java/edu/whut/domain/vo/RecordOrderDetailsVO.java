package edu.whut.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RecordOrderDetailsVO {
    private Long id; // 订单ID
    private String orderNumber; // 订单编号
    private String status; // 订单状态
    private String productName; // 商品名称
    private String productDescription;
    private BigDecimal price; // 订单价格
    private Date appointmentTime; // 预约时间
    private String userName; // 用户姓名
    private String phone; // 用户电话
    private String address; // 用户地址
    private Date createTime; // 下单时间
}