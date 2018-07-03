
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartLocation_ implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = -8335168457389649013L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StartLocation_() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public StartLocation_(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public StartLocation_ withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public StartLocation_ withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
