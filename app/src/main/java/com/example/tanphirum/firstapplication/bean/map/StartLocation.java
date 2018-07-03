
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartLocation implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = -9183315907531634990L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StartLocation() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public StartLocation(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public StartLocation withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public StartLocation withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
