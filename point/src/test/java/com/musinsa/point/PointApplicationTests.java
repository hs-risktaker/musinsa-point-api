package com.musinsa.point;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.point.domain.point.PointRepository;
import com.musinsa.point.dto.PointDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PointApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    PointRepository pointRepository;

    @Test
    @DisplayName("포인트 합계 조회 API 테스트")
    void getTotalPointTest() throws Exception{
        Long id = Long.valueOf(1234);

        mockMvc.perform(
                get("/musinsa/point/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("id", String.valueOf(id))

        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("포인트 합계 조회 API 에러 테스트")
    void getTotalPointErrTest() throws Exception{
        mockMvc.perform(
                get("/musinsa/point/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("id", "abc")
        ).andExpect(status().is(400)).andDo(print());
    }

    @Test
    @DisplayName("포인트 이력 조회 API 테스트")
    void getPointsTest() throws Exception{
        mockMvc.perform(
                get("/musinsa/point/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("id", "1234")
                        .queryParam("page", "5")
                        .queryParam("size", "30")
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("포인트 이력 조회 API 에러 테스트")
    void getPointsErrTest() throws Exception{
        mockMvc.perform(
                get("/musinsa/point/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("id", "ass")
                        .queryParam("page", "d")
                        .queryParam("size", "30")
        ).andExpect(status().is(400)).andDo(print());
    }
}
