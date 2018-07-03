
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg implements Serializable
{

    @SerializedName("distance")
    @Expose
    public Distance distance;
    @SerializedName("duration")
    @Expose
    public Duration duration;
    @SerializedName("end_address")
    @Expose
    public String endAddress;
    @SerializedName("end_location")
    @Expose
    public EndLocation endLocation;
    @SerializedName("start_address")
    @Expose
    public String startAddress;
    @SerializedName("start_location")
    @Expose
    public StartLocation startLocation;
    @SerializedName("steps")
    @Expose
    public List<Step> steps = null;
    @SerializedName("traffic_speed_entry")
    @Expose
    public List<Object> trafficSpeedEntry = null;
    @SerializedName("via_waypoint")
    @Expose
    public List<Object> viaWaypoint = null;
    private final static long serialVersionUID = 2684879353021038407L;

    public Leg withDistance(Distance distance) {
        this.distance = distance;
        return this;
    }

    public Leg withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Leg withEndAddress(String endAddress) {
        this.endAddress = endAddress;
        return this;
    }

    public Leg withEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public Leg withStartAddress(String startAddress) {
        this.startAddress = startAddress;
        return this;
    }

    public Leg withStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    public Leg withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public Leg withTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
        return this;
    }

    public Leg withViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
        return this;
    }

}
