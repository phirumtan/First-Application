
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Northeast implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = -5448998152063184117L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Northeast() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Northeast(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Northeast withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public Northeast withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
