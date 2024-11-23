package edu.whut.domain.dto;

import lombok.Data;

@Data
public class CheckUserAddDTO {
    private String name;
    private String gender;
    private String phone;
    private String idCard;
    private String address;

}
