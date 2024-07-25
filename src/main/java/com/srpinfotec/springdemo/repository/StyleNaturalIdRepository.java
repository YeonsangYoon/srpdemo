package com.srpinfotec.springdemo.repository;

import com.srpinfotec.springdemo.domain.order.Style;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class StyleNaturalIdRepository implements NaturalIdRepository{

    private final EntityManager em;

    public StyleNaturalIdRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Style> findByNaturalId(String styleNo) {
        return em.unwrap(Session.class)
                .byNaturalId(Style.class)
                .using("styleNo", styleNo)
                .loadOptional();
    }
}
