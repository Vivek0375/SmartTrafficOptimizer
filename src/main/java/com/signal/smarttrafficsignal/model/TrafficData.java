package com.signal.smarttrafficsignal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class TrafficData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime timestamp;

        private int congestionLevel; // e.g. 0–100%

        @ManyToOne
        @JoinColumn(name = "intersection_id")
        private Intersection intersection;
    }

