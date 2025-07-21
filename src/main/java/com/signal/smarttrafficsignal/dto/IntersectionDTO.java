package com.signal.smarttrafficsignal.dto;

    public class IntersectionDTO {

        private int id;
        private String intersection;
        private double latitude;
        private double longitude;
        private int green;
        private int red;
        private int yellow;
        private int congestion;

        public IntersectionDTO() {
        }

        public IntersectionDTO(int id, String intersection, double latitude, double longitude,
                               int green, int red, int yellow, int congestion) {
            this.id = id;
            this.intersection = intersection;
            this.latitude = latitude;
            this.longitude = longitude;
            this.green = green;
            this.red = red;
            this.yellow = yellow;
            this.congestion = congestion;
        }

        // Getters and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntersection() {
            return intersection;
        }

        public void setIntersection(String intersection) {
            this.intersection = intersection;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getYellow() {
            return yellow;
        }

        public void setYellow(int yellow) {
            this.yellow = yellow;
        }

        public int getCongestion() {
            return congestion;
        }

        public void setCongestion(int congestion) {
            this.congestion = congestion;
        }
    }

