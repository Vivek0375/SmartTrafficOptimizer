package com.signal.smarttrafficsignal.service;


//import com.traffic.model.Intersection;
//import com.traffic.model.TrafficData;
//import com.traffic.repository.IntersectionRepository;
//import com.traffic.repository.TrafficDataRepository;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.TrafficData;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.repository.TrafficDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

    @Service
    @RequiredArgsConstructor
    public class TrafficDataService {

        private final IntersectionRepository intersectionRepository;
        private final TrafficDataRepository trafficDataRepository;

        private final Random random = new Random();

        // Runs every 1 minute
        @Scheduled(fixedRate = 60000)
        public void fetchAndStoreMockTrafficData() {
            List<Intersection> intersections = intersectionRepository.findAll();

            if (intersections.isEmpty()) {
                System.out.println("⚠️ No intersections found. Add some first.");
                return;
            }

            for (Intersection intersection : intersections) {
                int congestionLevel = 20 + random.nextInt(70); // Range: 20% - 90%

                TrafficData trafficData = TrafficData.builder()
                        .intersection(intersection)
                        .timestamp(LocalDateTime.now())
                        .congestionLevel(congestionLevel)
                        .build();

                trafficDataRepository.save(trafficData);

                System.out.println("✅ Saved mock traffic data for: " + intersection.getName() +
                        " | Congestion: " + congestionLevel + "%");
            }
        }
    }


