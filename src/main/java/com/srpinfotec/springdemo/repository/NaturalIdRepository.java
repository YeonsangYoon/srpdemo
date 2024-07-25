package com.srpinfotec.springdemo.repository;

import com.srpinfotec.springdemo.domain.order.Style;

import java.util.Optional;

public interface NaturalIdRepository {
    Optional<Style> findByNaturalId(String styleNo);
}
