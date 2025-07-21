package com.signal.smarttrafficsignal.controller;


//import com.traffic.model.Intersection;
//import com.traffic.model.SignalTiming;
//import com.traffic.model.TrafficData;
//import com.traffic.repository.IntersectionRepository;
//import com.traffic.repository.SignalTimingRepository;
//import com.traffic.repository.TrafficDataRepository;
import com.signal.smarttrafficsignal.dto.SignalTimingDTO;
import com.signal.smarttrafficsignal.dto.TrafficDataDTO;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTiming;
import com.signal.smarttrafficsignal.model.TrafficData;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingRepository;
import com.signal.smarttrafficsignal.repository.TrafficDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

    @RestController
    @RequestMapping("/api/traffic")
    @RequiredArgsConstructor
    public class TrafficController {

        private final IntersectionRepository intersectionRepository;
        private final TrafficDataRepository trafficDataRepository;
        private final SignalTimingRepository signalTimingRepository;

        // 🧭 1. Get all intersections
        @GetMapping("/intersections")
        public List<Intersection> getAllIntersections() {
            return intersectionRepository.findAll();
        }

        @GetMapping("/traffic-data/{intersectionId}")
        public TrafficDataDTO getLatestTrafficData(@PathVariable Long intersectionId) {
            Intersection intersection = intersectionRepository.findById(intersectionId)
                    .orElseThrow(() -> new RuntimeException("Intersection not found"));

            TrafficData data = trafficDataRepository.findTopByIntersectionOrderByTimestampDesc(intersection)
                    .orElseThrow(() -> new RuntimeException("No traffic data available"));

            return TrafficDataDTO.builder()
                    .intersectionName(intersection.getName())
                    .congestionLevel(data.getCongestionLevel())
                    .timestamp(data.getTimestamp().toString())
                    .build();
        }


        @GetMapping("/signal-timing/{intersectionId}")
        public SignalTimingDTO getSignalTiming(@PathVariable Long intersectionId) {
            Intersection intersection = intersectionRepository.findById(intersectionId)
                    .orElseThrow(() -> new RuntimeException("Intersection not found"));

            SignalTiming timing = signalTimingRepository.findByIntersection(intersection)
                    .orElseThrow(() -> new RuntimeException("No signal timing available"));

            return SignalTimingDTO.builder()
                    .intersectionName(intersection.getName())
                    .greenTime(timing.getGreenTime())
                    .redTime(timing.getRedTime())
                    .yellowTime(timing.getYellowTime())
                    .lastUpdated(timing.getLastUpdated().toString())
                    .build();
        }

    }


