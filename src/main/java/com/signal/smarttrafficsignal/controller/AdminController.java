package com.signal.smarttrafficsignal.controller;


//import com.traffic.model.Intersection;
//import com.traffic.model.SignalTiming;
//import com.traffic.repository.IntersectionRepository;
//import com.traffic.repository.SignalTimingRepository;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTiming;
import com.signal.smarttrafficsignal.model.SignalTimingHistory;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingHistoryRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

    @RestController
    @RequestMapping("/admin")
    @RequiredArgsConstructor
        
    public class AdminController {

        private final IntersectionRepository intersectionRepository;
        private final SignalTimingRepository signalTimingRepository;

        // Create new intersection
        @PostMapping("/intersections")
        public Intersection createIntersection(@RequestBody Intersection intersection) {
            return intersectionRepository.save(intersection);
        }

        // Update intersection info
        @PutMapping("/intersections/{id}")
        public Intersection updateIntersection(@PathVariable Long id, @RequestBody Intersection updated) {
            Intersection existing = intersectionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Intersection not found"));
            existing.setName(updated.getName());
            existing.setCity(updated.getCity());
            existing.setLocation(updated.getLocation());
            existing.setActive(updated.isActive());
            return intersectionRepository.save(existing);
        }

        // Manually override signal timing
        @Autowired
        private SignalTimingHistoryRepository historyRepository;

        @PutMapping("/override-signal/{intersectionId}")
        public SignalTiming overrideSignal(@PathVariable Long intersectionId,
                                           @RequestParam int green,
                                           @RequestParam int red,
                                           @RequestParam int yellow) {

            Intersection intersection = intersectionRepository.findById(intersectionId)
                    .orElseThrow(() -> new RuntimeException("Intersection not found"));

            SignalTiming timing = signalTimingRepository.findByIntersection(intersection)
                    .orElse(new SignalTiming());

            // 🧠 Save current timing to history (only if existing)
            if (timing.getId() != null) {
                SignalTimingHistory history = SignalTimingHistory.builder()
                        .intersection(intersection)
                        .greenTime(timing.getGreenTime())
                        .redTime(timing.getRedTime())
                        .yellowTime(timing.getYellowTime())
                        .reason("Manual Override")
                        .recordedAt(LocalDateTime.now())
                        .build();
                historyRepository.save(history);
            }

            // Now override with new values
            timing.setIntersection(intersection);
            timing.setGreenTime(green);
            timing.setRedTime(red);
            timing.setYellowTime(yellow);
            timing.setLastUpdated(LocalDateTime.now());

            return signalTimingRepository.save(timing);
        }


    }
