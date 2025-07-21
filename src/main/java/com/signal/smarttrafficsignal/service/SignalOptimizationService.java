package com.signal.smarttrafficsignal.service;

import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTiming;
import com.signal.smarttrafficsignal.model.TrafficData;
import com.signal.smarttrafficsignal.model.SignalTimingHistory;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingHistoryRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingRepository;
import com.signal.smarttrafficsignal.repository.TrafficDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignalOptimizationService {

    @Autowired
    private SignalTimingHistoryRepository historyRepository;

    private final IntersectionRepository intersectionRepository;
    private final TrafficDataRepository trafficDataRepository;
    private final SignalTimingRepository signalTimingRepository;

    // Runs every 1 minute after traffic data is generated
    @Scheduled(fixedRate = 60000, initialDelay = 5000)
    public void optimizeSignals() {
        List<Intersection> intersections = intersectionRepository.findAll();

        for (Intersection intersection : intersections) {
            TrafficData latestData = trafficDataRepository
                    .findTopByIntersectionOrderByTimestampDesc(intersection)
                    .orElse(null);

            if (latestData == null) continue;

            int congestion = latestData.getCongestionLevel();

            int green = 30, red = 30, yellow = 5;

            if (congestion >= 80) {
                green = 90;
                red = 60;
            } else if (congestion >= 60) {
                green = 75;
                red = 60;
            } else if (congestion >= 40) {
                green = 60;
                red = 50;
            } else if (congestion >= 20) {
                green = 45;
                red = 45;
            }

            SignalTiming existing = signalTimingRepository.findByIntersection(intersection)
                    .orElse(null);

            if (existing != null) {
                // 🧠 Save previous values to history
                SignalTimingHistory history = SignalTimingHistory.builder()
                        .intersection(intersection)
                        .greenTime(existing.getGreenTime())
                        .redTime(existing.getRedTime())
                        .yellowTime(existing.getYellowTime())
                        .reason("Auto Optimization")
                        .recordedAt(LocalDateTime.now())
                        .build();
                historyRepository.save(history);

                existing.setGreenTime(green);
                existing.setRedTime(red);
                existing.setYellowTime(yellow);
                existing.setLastUpdated(LocalDateTime.now());
                signalTimingRepository.save(existing);
            } else {
                SignalTiming timing = SignalTiming.builder()
                        .intersection(intersection)
                        .greenTime(green)
                        .redTime(red)
                        .yellowTime(yellow)
                        .lastUpdated(LocalDateTime.now())
                        .build();
                signalTimingRepository.save(timing);
            }

            System.out.println("\uD83D\uDEA6 Optimized signal for " + intersection.getName()
                    + " → Green: " + green + "s | Red: " + red + "s | Yellow: " + yellow + "s");
        }
    }
}
