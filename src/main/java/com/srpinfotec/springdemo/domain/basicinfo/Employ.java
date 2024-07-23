package com.srpinfotec.springdemo.domain.basicinfo;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employ extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employCd;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dept dept;

    private String userId;
    private String password;

    private String email;

    private LocalDate enterDt;
    private LocalDate retireDt;
}
