package com.signal.smarttrafficsignal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class SignalTiming {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int greenTime;  // in seconds
        private int redTime;
        private int yellowTime;

        private LocalDateTime lastUpdated;

        @OneToOne
        @JoinColumn(name = "intersection_id")
        private Intersection intersection;
    }

