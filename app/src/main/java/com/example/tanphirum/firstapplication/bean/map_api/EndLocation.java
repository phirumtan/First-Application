
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = -7112093147798380714L;

    public EndLocation withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public EndLocation withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
