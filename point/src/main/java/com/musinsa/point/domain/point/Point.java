package com.musinsa.point.domain.point;

import com.musinsa.point.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MS_TB_POINT")
public class Point extends BaseEntity {
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String pointType;

    @Column(nullable = false)
    private int point;

}
