package com.srpinfotec.springdemo.domain.basicinfo;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 공통 코드 객체
 */
@Entity
@Table(name = "CM_COD_MNG")
@Getter
public class CommonCode extends BaseEntity {
    @Id
    private Long cmCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UP_CODE")
    private CommonCode parent;

    @OneToMany(mappedBy = "parent")
    private List<CommonCode> child = new ArrayList<>();

    private String nameKor;
    private String nameEng;

    private String section1;
    private String section2;
    private String section3;

}
