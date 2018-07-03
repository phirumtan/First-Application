
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Southwest implements Serializable
{

    @SerializedName("lat")
    @Expose
    public float lat;
    @SerializedName("lng")
    @Expose
    public float lng;
    private final static long serialVersionUID = -2873239711167790534L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Southwest() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Southwest(float lat, float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Southwest withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public Southwest withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
