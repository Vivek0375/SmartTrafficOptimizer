package com.signal.smarttrafficsignal.dto;


import lombok.*;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class SignalTimingDTO {
        private String intersectionName;
        private int greenTime;
        private int redTime;
        private int yellowTime;
        private String lastUpdated;
    }


