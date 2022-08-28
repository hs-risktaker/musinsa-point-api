package com.musinsa.point.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PointDetailDto {
    private Long id;

    @NotNull
    private Long userId;

    private String pointType;

    @NotNull
    private int point;

    private LocalDateTime creationTime;

    private LocalDateTime modifiedTime;
}
