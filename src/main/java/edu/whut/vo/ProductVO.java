package edu.whut.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductVO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer isListed;
    private Integer isRecommended;
    private LocalDateTime updateTime;
}
