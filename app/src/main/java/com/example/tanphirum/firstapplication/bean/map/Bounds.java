
package com.example.tanphirum.firstapplication.bean.map;

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
    private final static long serialVersionUID = 2691663536962420032L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bounds() {
    }

    /**
     * 
     * @param southwest
     * @param northeast
     */
    public Bounds(Northeast northeast, Southwest southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Bounds withNortheast(Northeast northeast) {
        this.northeast = northeast;
        return this;
    }

    public Bounds withSouthwest(Southwest southwest) {
        this.southwest = southwest;
        return this;
    }

}
