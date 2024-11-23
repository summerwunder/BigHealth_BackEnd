package edu.whut.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecordDetailsVO {
    private Long productId;
    private String productName;
    private String checkItem;
    private String store;
    private Date time;
}
