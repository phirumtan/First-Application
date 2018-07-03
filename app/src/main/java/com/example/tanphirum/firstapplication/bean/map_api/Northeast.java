
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = -5342741558356022393L;

    public Northeast withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public Northeast withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
