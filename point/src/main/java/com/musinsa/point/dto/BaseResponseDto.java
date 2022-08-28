package com.musinsa.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
public class BaseResponseDto<T>{

    @Builder.Default
    private String resultCode =  "200";

    @Builder.Default
    private String resultMessage = "Success";

    private T data;
}
