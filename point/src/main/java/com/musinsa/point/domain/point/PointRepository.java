package com.musinsa.point.domain.point;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByIdAndPointTypeAndUserId(Long id, String pointType, Long userId);

    List<Point> findAllByUserIdAndPointTypeOrderByCreationTimeDesc(Long userId, String pointType, Pageable pageable);
    List<Point> findAllByUserIdOrderByCreationTimeDesc(Long userId, Pageable pageable);

    @Query(value =
        "SELECT SUM(point) FROM MS_TB_POINT WHERE point_type='U' AND user_id=?1 AND creation_time >= ?2 group by user_id", nativeQuery = true
    )
    Integer findSumUsedPoint(Long userId, LocalDate dt);

    @Query(value =
            "SELECT SUM(point) FROM MS_TB_POINT WHERE point_type='S' AND user_id=?1 AND creation_time >= ?2 group by user_id", nativeQuery = true
    )
    Integer findSumSavedPoint(Long userId, LocalDate dt);

    void deleteById(Long id);
}
