package com.signal.smarttrafficsignal.repository;

//import com.traffic.model.SignalTimingHistory;
//import com.traffic.model.Intersection;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTimingHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

    public interface SignalTimingHistoryRepository extends JpaRepository<SignalTimingHistory, Long> {
        List<SignalTimingHistory> findByIntersectionOrderByRecordedAtDesc(Intersection intersection);

        Page<SignalTimingHistory> findByIntersectionAndRecordedAtBetween(Intersection intersection, LocalDateTime start, LocalDateTime end, Pageable pageable);

        List<SignalTimingHistory> findTop10ByIntersectionOrderByRecordedAtDesc(Intersection intersection);


    }

