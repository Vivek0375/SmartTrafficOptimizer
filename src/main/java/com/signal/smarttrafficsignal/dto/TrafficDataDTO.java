package com.signal.smarttrafficsignal.dto;


import lombok.*;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class TrafficDataDTO {
        private String intersectionName;
        private int congestionLevel;
        private String timestamp;
    }


