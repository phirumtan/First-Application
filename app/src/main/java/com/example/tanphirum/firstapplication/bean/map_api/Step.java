
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step implements Serializable
{

    @SerializedName("distance")
    @Expose
    public Distance_ distance;
    @SerializedName("duration")
    @Expose
    public Duration_ duration;
    @SerializedName("end_location")
    @Expose
    public EndLocation_ endLocation;
    @SerializedName("html_instructions")
    @Expose
    public String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    public Polyline polyline;
    @SerializedName("start_location")
    @Expose
    public StartLocation_ startLocation;
    @SerializedName("travel_mode")
    @Expose
    public String travelMode;
    @SerializedName("maneuver")
    @Expose
    public String maneuver;
    private final static long serialVersionUID = -2034809636876485870L;

    public Step withDistance(Distance_ distance) {
        this.distance = distance;
        return this;
    }

    public Step withDuration(Duration_ duration) {
        this.duration = duration;
        return this;
    }

    public Step withEndLocation(EndLocation_ endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public Step withHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
        return this;
    }

    public Step withPolyline(Polyline polyline) {
        this.polyline = polyline;
        return this;
    }

    public Step withStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    public Step withTravelMode(String travelMode) {
        this.travelMode = travelMode;
        return this;
    }

    public Step withManeuver(String maneuver) {
        this.maneuver = maneuver;
        return this;
    }

}
