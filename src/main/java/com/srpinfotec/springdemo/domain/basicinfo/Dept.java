package com.srpinfotec.springdemo.domain.basicinfo;

import com.srpinfotec.springdemo.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dept extends BaseEntity {
    @Id
    @Column(name = "DEPT_CODE")
    private Long deptCode;

    @OneToMany(mappedBy = "dept")
    List<Employ> employs = new ArrayList<>();
}
