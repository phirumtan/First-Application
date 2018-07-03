
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline implements Serializable
{

    @SerializedName("points")
    @Expose
    public String points;
    private final static long serialVersionUID = -4347469587436789361L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OverviewPolyline() {
    }

    /**
     * 
     * @param points
     */
    public OverviewPolyline(String points) {
        super();
        this.points = points;
    }

    public OverviewPolyline withPoints(String points) {
        this.points = points;
        return this;
    }

}
