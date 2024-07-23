package com.srpinfotec.springdemo.domain.basicinfo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommonCodeTest {
    @Autowired
    EntityManager em;

    @BeforeEach
    public void beforeTest(){

    }
}