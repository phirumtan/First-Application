
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EndLocation_ implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = -2366865534301839591L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EndLocation_() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public EndLocation_(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public EndLocation_ withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public EndLocation_ withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
