package edu.whut.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RecordAddDTO {
    private Long checkUserId;
    private Long productId;
    private Date appointmentTime;
}
