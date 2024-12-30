package edu.whut.domain.dto;

import lombok.Data;

@Data
public class UpdateStatusDTO {
    private Long orderDetailsId; // 订单详情ID
    private String newStatus;    // 新的状态
}
