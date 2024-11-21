package edu.whut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UserDTO {

    private String nickname;

    private String realName;

    private String gender;

    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    private String idCard;

    private String address;

    private Integer isMember;

    private Integer isRecommender;
}
