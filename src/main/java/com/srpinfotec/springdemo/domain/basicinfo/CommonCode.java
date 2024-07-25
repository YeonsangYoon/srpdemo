package com.srpinfotec.springdemo.domain.basicinfo;

import com.srpinfotec.springdemo.domain.BaseEntity;
import com.srpinfotec.springdemo.dto.CmCodeDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

/**
 * 공통 코드 객체
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"cmCode", "nameKor"})

@Entity
@Table(name = "CM_COD_MNG")
public class CommonCode extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "CODE_ID")
    private Long id;

    @NaturalId
    @Column(name = "CM_CODE", unique = true, updatable = false)
    private String cmCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UP_CODE")
    private CommonCode parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CommonCode> child = new ArrayList<>();

    private String nameKor;
    private String nameEng;

    private String section1;
    private String section2;
    private String section3;

    public CommonCode(String cmCode) {
        this.cmCode = cmCode;
    }

    public CommonCode(String cmCode, String nameKor, String nameEng, String section1, String section2, String section3) {
        this.cmCode = cmCode;
        this.nameKor = nameKor;
        this.nameEng = nameEng;
        this.section1 = section1;
        this.section2 = section2;
        this.section3 = section3;
    }

    public CommonCode(CmCodeDto codeDto) {
        this.cmCode = codeDto.getCmCode();
        this.nameKor = codeDto.getNameKor();
        this.nameEng = codeDto.getNameEng();
        this.section1 = codeDto.getSection1();
        this.section2 = codeDto.getSection2();
        this.section3 = codeDto.getSection3();
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setSection1(String section1) {
        this.section1 = section1;
    }

    public void setSection2(String section2) {
        this.section2 = section2;
    }

    public void setSection3(String section3) {
        this.section3 = section3;
    }

    /*****************************************************************************************
     * CusTom Method
     *****************************************************************************************/

    /**
     * Parent 변경
     * @param parent
     */
    public void setParent(CommonCode parent){
        // 기존가 있다면 해당 부모의 자식 목록에서 삭제
        if(this.parent != null){
            this.parent.getChild().remove(this);
        }

        this.parent = parent;
        parent.getChild().add(this);
    }

    /**
     * 자식 추가
     */
    public CommonCode addNewChild(){
        String newCmCode = this.getCmCode() + StringUtils.leftPad(String.valueOf(this.getChild().size() + 1), 3, '0' );

        CommonCode child = new CommonCode(newCmCode);

        child.setParent(this);

        return child;
    }

    public CmCodeDto toDto(){
        CmCodeDto dto = new CmCodeDto();

        dto.setCmCode(this.cmCode);
        dto.setNameKor(this.nameKor);
        dto.setNameEng(this.nameEng);
        dto.setSection1(this.section1);
        dto.setSection2(this.section2);
        dto.setSection3(this.section3);

        if(this.getParent() != null){
            dto.setUpCode(this.parent.getCmCode());
        }

        return dto;
    }
}
