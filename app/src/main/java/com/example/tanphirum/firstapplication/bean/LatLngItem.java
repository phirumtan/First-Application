package com.example.tanphirum.firstapplication.bean;

import java.util.Locale;

public class LatLngItem {
    public Double latitude;
    public Double longitude;

    public LatLngItem() {

    }

    public LatLngItem(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLngItem withLat(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LatLngItem withLng(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public String toString() {
        // Enforce Locale to English for double to string conversion
        return String.format(Locale.ENGLISH, "%.8f,%.8f", latitude, longitude);
    }
}
