package com.srpinfotec.springdemo.repository;

import com.srpinfotec.springdemo.domain.basicinfo.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    @Query("SELECT c FROM CommonCode c LEFT JOIN FETCH c.parent")
    List<CommonCode> findAllWithParent();
    Optional<CommonCode> findById(long id);

    Optional<CommonCode> findByCmCode(String cmCode);

    @Query("SELECT c.parent FROM CommonCode c WHERE c.cmCode = :cmCode")
    Optional<CommonCode> findParent(@Param("cmCode") String cmCode);

    @Query("SELECT c FROM CommonCode c WHERE c.parent.cmCode = :cmCode")
    List<CommonCode> findChildByParentCmCode(@Param("cmCode") String cmCode);
}
