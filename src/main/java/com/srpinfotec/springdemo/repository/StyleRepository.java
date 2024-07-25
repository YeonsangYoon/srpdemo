package com.srpinfotec.springdemo.repository;

import com.srpinfotec.springdemo.domain.order.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StyleRepository extends JpaRepository<Style, Long>, NaturalIdRepository {

    Optional<Style> findByStyleNo(String styleNo);
}
