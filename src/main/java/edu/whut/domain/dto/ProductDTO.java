package edu.whut.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private String type;
    private BigDecimal price;
    private String description;
    private String details;
    private Integer isListed; // 0: 否, 1: 是
    private Integer isRecommended; // 0: 否, 1: 是
}
