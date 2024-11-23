package edu.whut.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrdersVO {
        private Long id;
        private String orderNumber;
        private String name;
        private String gender;
        private String phone;
        private String address;
        private String productName;
        private String status;
        private Date orderTime;
        private BigDecimal price;
}
