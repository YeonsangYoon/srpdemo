package com.srpinfotec.springdemo.domain.order;

import com.srpinfotec.springdemo.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class StyleTest {
    @Autowired
    StyleRepository repository;

    @Autowired
    EntityManager em;

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    public void init() {
        Style style1 = new Style("Style1");
        Style style2 = new Style("Style2");
        Style style3 = new Style("Style3");
        Style style4 = new Style("Style4");

        em.persist(style1);
        em.persist(style2);
        em.persist(style3);
        em.persist(style4);

        em.flush();
        em.clear();

        log.info("저장 끝 ===================================================================================");
    }


    @Test
    public void id조회(){
        Style style1 = repository.findById(1L).get();       // 총 select query 한번 나간다 (1차 캐시에서 조회)
        Style style2 = repository.findById(1L).get();
        Style style3 = repository.findById(1L).get();
        Style style4 = repository.findById(1L).get();


        assertThat(style1).isEqualTo(style2);
        assertThat(style1).isEqualTo(style3);
        assertThat(style1).isEqualTo(style4);
    }

    @Test
    public void column조회(){
        Style style1 = repository.findByStyleNo("Style1").get();    // Column 조회는 같은 엔티티여도 무조건 select 쿼리가 나간다
        Style style2 = repository.findByStyleNo("Style1").get();
        Style style3 = repository.findByStyleNo("Style1").get();
        Style style4 = repository.findByStyleNo("Style1").get();

        assertThat(style1).isEqualTo(style2);
        assertThat(style1).isEqualTo(style3);
    }

    @Test
    public void naturalKey조회(){
        Style style1 = repository.findByNaturalId("Style1").get();
        Style style2 = repository.findByNaturalId("Style1").get();
        Style style3 = repository.findByNaturalId("Style1").get();
        Style style4 = repository.findByNaturalId("Style1").get();

        assertThat(style1).isEqualTo(style2);
        assertThat(style1).isEqualTo(style3);
        assertThat(style1).isEqualTo(style4);
    }
}