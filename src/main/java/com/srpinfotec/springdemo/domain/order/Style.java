package com.srpinfotec.springdemo.domain.order;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Style extends BaseEntity {
    @Id
    private Long orderSno;

    private String styleNo;
    private String fileNo;

}
