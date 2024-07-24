package com.srpinfotec.springdemo.domain.order;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Style extends BaseEntity {
    @Id
    private Long orderSno;

    private String styleNo;
    private String fileNo;

    public Style(String styleNo, String fileNo) {
        this.styleNo = styleNo;
        this.fileNo = fileNo;
    }
}
