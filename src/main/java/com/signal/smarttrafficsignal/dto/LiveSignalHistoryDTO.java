package com.signal.smarttrafficsignal.dto;

import java.time.LocalDateTime;

public class LiveSignalHistoryDTO {
    private LocalDateTime recordedAt;
    private int greenTime;
    private int redTime;
    private int yellowTime;
    private String reason;

    // Constructor
    public LiveSignalHistoryDTO(LocalDateTime recordedAt, int greenTime, int redTime, int yellowTime, String reason) {
        this.recordedAt = recordedAt;
        this.greenTime = greenTime;
        this.redTime = redTime;
        this.yellowTime = yellowTime;
        this.reason = reason;
    }

    // Getters and setters
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public int getGreenTime() { return greenTime; }
    public void setGreenTime(int greenTime) { this.greenTime = greenTime; }

    public int getRedTime() { return redTime; }
    public void setRedTime(int redTime) { this.redTime = redTime; }

    public int getYellowTime() { return yellowTime; }
    public void setYellowTime(int yellowTime) { this.yellowTime = yellowTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
