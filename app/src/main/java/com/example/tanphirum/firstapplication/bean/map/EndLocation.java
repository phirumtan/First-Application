
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EndLocation implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = 7800163796183650380L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EndLocation() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public EndLocation(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public EndLocation withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public EndLocation withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
