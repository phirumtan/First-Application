
package com.example.tanphirum.firstapplication.bean.map_api;

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
    private final static long serialVersionUID = -1438457170326483664L;

    public Southwest withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public Southwest withLng(float lng) {
        this.lng = lng;
        return this;
    }

}
