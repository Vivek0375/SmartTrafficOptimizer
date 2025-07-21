package com.signal.smarttrafficsignal.dto;


import lombok.*;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class IntersectionDashboardDTO {
        private Long id;
        private String intersection;
        private Double latitude;
        private Double longitude;
        private int green;
        private int red;
        private int yellow;
        private int congestion;
    }
