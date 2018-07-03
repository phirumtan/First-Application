
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = -5054984418519893500L;

    public EndLocation_ withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public EndLocation_ withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
