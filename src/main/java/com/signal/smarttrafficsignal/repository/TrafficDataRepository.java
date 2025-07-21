package com.signal.smarttrafficsignal.repository;


import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.TrafficData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrafficDataRepository extends JpaRepository<TrafficData, Long> {
    Optional<TrafficData> findTopByIntersectionOrderByTimestampDesc(Intersection intersection);
}
