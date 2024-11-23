package edu.whut.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecordVO {
    private Long id;
    private String name;          // 体检人姓名
    private String gender;        // 体检人性别
    private String phone;         // 体检人手机号
    private String idCard;        // 体检人身份证号
    private String productName;   // 商品名称
    private Date appointmentTime; // 预约时间
    private String status;        // 预约状态
    private Date createTime;      // 创建时间
}
