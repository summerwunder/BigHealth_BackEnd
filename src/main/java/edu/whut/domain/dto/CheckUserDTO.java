package edu.whut.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CheckUserDTO {
    private Integer id;

    private String name;

    private String gender;

    private String phone;

    private String idCard;

    private Integer checkCount = 0;
}
