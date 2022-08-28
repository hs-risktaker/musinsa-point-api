package com.musinsa.point.service;

import com.musinsa.point.constant.ErrorCode;
import com.musinsa.point.domain.point.Point;
import com.musinsa.point.domain.point.PointRepository;
import com.musinsa.point.dto.CancelPointRequestDto;
import com.musinsa.point.dto.PointDetailDto;
import com.musinsa.point.dto.TotalPointDto;
import com.musinsa.point.exception.APIException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointService {

    private final ModelMapper modelMapper;

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository, ModelMapper modelMapper) {
        this.pointRepository = pointRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public TotalPointDto getTotalPoint(Long userId) throws Exception{
        TotalPointDto totalPoint = new TotalPointDto();
        LocalDate expiredDate = LocalDate.now().minusYears(1);
        Integer usedPoint = pointRepository.findSumUsedPoint(userId, expiredDate);
        Integer savedPoint = pointRepository.findSumSavedPoint(userId, expiredDate);

        if (savedPoint != null) {
            if (usedPoint == null) {
                totalPoint.setPoint(savedPoint);
            } else {
                totalPoint.setPoint(savedPoint - usedPoint);
            }
        }
        return totalPoint;
    }

    @Transactional(readOnly = true)
    public List<PointDetailDto> getPointDetail(Long userId, String pointType, Pageable pageable) throws Exception{
        List<Point> points = null;
        List<PointDetailDto> pointList = new ArrayList<>();

        if(pointType == null){
            points = pointRepository.findAllByUserIdOrderByCreationTimeDesc(userId, pageable);
        } else{
            if(!pointType.equals("S") && !pointType.equals("U")){
                throw new APIException(ErrorCode.E1001.getCode(), ErrorCode.E1001.getErrMsg());
            }
            points = pointRepository.findAllByUserIdAndPointTypeOrderByCreationTimeDesc(userId, pointType, pageable);
        }

        pointList = modelMapper.map(points, new TypeToken<List<PointDetailDto>>(){}.getType());
        return pointList;
    }


    @Transactional()
    public PointDetailDto updatePoint(PointDetailDto param) throws Exception {

        if (param.getPointType().isEmpty()){
            throw new APIException(ErrorCode.E1001.getCode(), ErrorCode.E1001.getErrMsg());
        }

        if (!param.getPointType().equals("U") && !param.getPointType().equals("S")){
            throw new APIException(ErrorCode.E1001.getCode(), ErrorCode.E1001.getErrMsg());
        }

        if(param.getPointType().equals("U")){
            if(getTotalPoint(param.getUserId()).getPoint() - param.getPoint() < 0){
                throw new APIException(ErrorCode.E1002.getCode(), ErrorCode.E1002.getErrMsg());
            }
        }

        Point p = Point.builder().userId(param.getUserId()).point(param.getPoint()).pointType(param.getPointType()).build();
        pointRepository.saveAndFlush(p);

        PointDetailDto result = modelMapper.map(p, PointDetailDto.class);
        return result;
    }


    @Transactional()
    public boolean canclePoint(CancelPointRequestDto param) throws Exception{
        param.setPointType("U");
        Optional<Point> t = pointRepository.findByIdAndPointTypeAndUserId(param.getId(), param.getPointType(), param.getUserId());

        if(t.isEmpty()){
            throw new APIException(ErrorCode.E1003.getCode(), ErrorCode.E1003.getErrMsg());
        }

        pointRepository.deleteById(param.getId());
        return true;
    }
}
