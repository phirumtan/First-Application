
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bounds implements Serializable
{

    @SerializedName("northeast")
    @Expose
    public Northeast northeast;
    @SerializedName("southwest")
    @Expose
    public Southwest southwest;
    private final static long serialVersionUID = -8436519039085767941L;

    public Bounds withNortheast(Northeast northeast) {
        this.northeast = northeast;
        return this;
    }

    public Bounds withSouthwest(Southwest southwest) {
        this.southwest = southwest;
        return this;
    }

}
