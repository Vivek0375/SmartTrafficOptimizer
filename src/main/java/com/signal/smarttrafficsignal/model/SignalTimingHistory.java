package com.signal.smarttrafficsignal.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class SignalTimingHistory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Intersection intersection;

        private int greenTime;
        private int redTime;
        private int yellowTime;

        private String reason; // e.g., "Manual Override", "Auto Optimization"
        private LocalDateTime recordedAt;
    }


