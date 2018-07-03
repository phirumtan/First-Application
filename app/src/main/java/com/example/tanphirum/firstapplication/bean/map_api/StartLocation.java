
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = 7557151996996527950L;

    public StartLocation withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public StartLocation withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
