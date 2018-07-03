
package com.example.tanphirum.firstapplication.bean.map;

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
    @SerializedName("endLocation")
    @Expose
    public EndLocation_ endLocation;
    @SerializedName("htmlInstructions")
    @Expose
    public String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    public Polyline polyline;
    @SerializedName("startLocation")
    @Expose
    public StartLocation_ startLocation;
    @SerializedName("travelMode")
    @Expose
    public String travelMode;
    @SerializedName("maneuver")
    @Expose
    public String maneuver;
    private final static long serialVersionUID = 7308415992865686161L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Step() {
    }

    /**
     * 
     * @param duration
     * @param distance
     * @param polyline
     * @param endLocation
     * @param htmlInstructions
     * @param startLocation
     * @param maneuver
     * @param travelMode
     */
    public Step(Distance_ distance, Duration_ duration, EndLocation_ endLocation, String htmlInstructions, Polyline polyline, StartLocation_ startLocation, String travelMode, String maneuver) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.endLocation = endLocation;
        this.htmlInstructions = htmlInstructions;
        this.polyline = polyline;
        this.startLocation = startLocation;
        this.travelMode = travelMode;
        this.maneuver = maneuver;
    }

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
