package com.srpinfotec.springdemo.domain.order;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Style extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "ORD_SNO")
    private Long orderSno;

    @NaturalId
    @Column(name = "STYLE_NO", unique = true)
    private String styleNo;

    @Column(name = "FILE_NO")
    private String fileNo;

    public Style(String styleNo, String fileNo) {
        this.styleNo = styleNo;
        this.fileNo = fileNo;
    }

    public Style(String styleNo) {
        this.styleNo = styleNo;
    }
}
