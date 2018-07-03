
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyline implements Serializable
{

    @SerializedName("points")
    @Expose
    public String points;
    private final static long serialVersionUID = 7550648053276670015L;

    public Polyline withPoints(String points) {
        this.points = points;
        return this;
    }

}
