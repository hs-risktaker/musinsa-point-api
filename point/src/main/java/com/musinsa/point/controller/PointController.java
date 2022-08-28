package com.musinsa.point.controller;

import com.musinsa.point.dto.BaseResponseDto;
import com.musinsa.point.dto.CancelPointRequestDto;
import com.musinsa.point.dto.PointDetailDto;
import com.musinsa.point.dto.TotalPointDto;
import com.musinsa.point.service.PointService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/musinsa/point")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/total")
    public ResponseEntity getTotalPoint(@RequestParam Long id) {
        BaseResponseDto<TotalPointDto> dt;
        try{
            TotalPointDto result = pointService.getTotalPoint(id);
            dt = BaseResponseDto.<TotalPointDto>builder().data(result).build();
            return new ResponseEntity<>(dt, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity getPointDetail(@RequestParam Long id, @RequestParam(required = false) String pointType, Pageable pageable) {
        BaseResponseDto<List<PointDetailDto>> dt;
        try{
            List<PointDetailDto> result = pointService.getPointDetail(id, pointType, pageable);
            dt = BaseResponseDto.<List<PointDetailDto>>builder().data(result).build();
            return new ResponseEntity<>(dt, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/use")
    public ResponseEntity usePoint(@RequestBody PointDetailDto param) {
        BaseResponseDto<PointDetailDto> dt;
        try{
            param.setPointType("U");
            PointDetailDto result = pointService.updatePoint(param);
            dt = BaseResponseDto.<PointDetailDto>builder().data(result).build();
            return new ResponseEntity<>(dt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity savePoint(@RequestBody PointDetailDto param) {
        BaseResponseDto<PointDetailDto> dt;
        try{
            param.setPointType("S");
            PointDetailDto result = pointService.updatePoint(param);
            dt = BaseResponseDto.<PointDetailDto>builder().data(result).build();
            return new ResponseEntity<>(dt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cancel")
    public ResponseEntity cancelPoint(@RequestBody CancelPointRequestDto param){
        BaseResponseDto<Boolean> dt;
        try{
            boolean result = pointService.canclePoint(param);
            dt = BaseResponseDto.<Boolean>builder().data(result).build();
            return new ResponseEntity<>(dt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
