
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyline implements Serializable
{

    @SerializedName("points")
    @Expose
    public String points;
    private final static long serialVersionUID = 5627185154035594556L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Polyline() {
    }

    /**
     * 
     * @param points
     */
    public Polyline(String points) {
        super();
        this.points = points;
    }

    public Polyline withPoints(String points) {
        this.points = points;
        return this;
    }

}
