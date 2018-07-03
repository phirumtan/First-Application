
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = -7041210144937700675L;

    public StartLocation_ withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public StartLocation_ withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
