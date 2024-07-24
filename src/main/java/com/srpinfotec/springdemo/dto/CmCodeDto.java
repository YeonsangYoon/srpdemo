package com.srpinfotec.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmCodeDto {
    private String cmCode;
    private String upCode;

    private String nameKor;
    private String nameEng;

    private String section1;
    private String section2;
    private String section3;

    public CmCodeDto(String cmCode, String upCode, String nameKor) {
        this.cmCode = cmCode;
        this.upCode = upCode;
        this.nameKor = nameKor;
    }
}
