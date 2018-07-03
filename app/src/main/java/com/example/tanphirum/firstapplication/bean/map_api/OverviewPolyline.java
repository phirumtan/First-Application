
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline implements Serializable
{

    @SerializedName("points")
    @Expose
    public String points;
    private final static long serialVersionUID = -6145607722589712919L;

    public OverviewPolyline withPoints(String points) {
        this.points = points;
        return this;
    }

}
