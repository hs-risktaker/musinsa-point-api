package com.musinsa.point.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelPointRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    private String pointType;
}
