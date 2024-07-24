package com.srpinfotec.springdemo.common;

import com.srpinfotec.springdemo.domain.basicinfo.CommonCode;
import com.srpinfotec.springdemo.dto.CmCodeDto;
import com.srpinfotec.springdemo.repository.CommonCodeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Commit
@Transactional
@SpringBootTest
class CommonCodeServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    EntityManagerFactory emf;

    @Autowired CommonCodeService commonCodeService;
    @Autowired CommonCodeRepository commonCodeRepository;

    @BeforeEach
    public void before(){
        CommonCode code1 = new CommonCode("TAB");
        CommonCode code2 = code1.addNewChild();
        CommonCode code3 = code1.addNewChild();
        CommonCode code4 = code2.addNewChild();
        CommonCode code5 = code2.addNewChild();
        CommonCode code6 = code3.addNewChild();
        CommonCode code7 = code3.addNewChild();

        em.persist(code1);
        em.persist(code2);
        em.persist(code3);
        em.persist(code4);
        em.persist(code5);
        em.persist(code6);
        em.persist(code7);

        em.flush();
        em.clear();

        log.info("DATA LOAD OVER");
    }
    

    @Test
    public void 단순_조회(){
        Map<String, CmCodeDto> tab = commonCodeService.searchCommonCode("TAB");
        Map<String, CmCodeDto> tab001 = commonCodeService.searchCommonCode("TAB001");
        Map<String, CmCodeDto> tab002 = commonCodeService.searchCommonCode("TAB002");

        for(CmCodeDto dto : tab.values()){
            System.out.println("dto = " + dto);
        }

        for(CmCodeDto dto : tab001.values()){
            System.out.println("dto = " + dto);
        }

        for(CmCodeDto dto : tab002.values()){
            System.out.println("dto = " + dto);
        }

        assertThat(tab.size()).isEqualTo(2);
        assertThat(tab001.size()).isEqualTo(2);
        assertThat(tab002.size()).isEqualTo(2);
    }

    @Test
    public void 수정(){
        CmCodeDto dto = new CmCodeDto("TAB001001", "TAB002", "코드1");

        commonCodeService.updateCode(dto);

        CommonCode code = commonCodeRepository.findByCmCode("TAB001001").get();

        assertThat(code.getParent().getCmCode()).isEqualTo("TAB002");
    }
}
