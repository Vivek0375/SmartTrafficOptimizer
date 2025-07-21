package com.signal.smarttrafficsignal.repository;


import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTiming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignalTimingRepository extends JpaRepository<SignalTiming, Long> {


        Optional<SignalTiming> findByIntersection(Intersection intersection);

    }


