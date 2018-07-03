
package com.example.tanphirum.firstapplication.bean.map;

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
    @SerializedName("endAddress")
    @Expose
    public String endAddress;
    @SerializedName("endLocation")
    @Expose
    public EndLocation endLocation;
    @SerializedName("startAddress")
    @Expose
    public String startAddress;
    @SerializedName("startLocation")
    @Expose
    public StartLocation startLocation;
    @SerializedName("steps")
    @Expose
    public List<Step> steps = null;
    private final static long serialVersionUID = -7957997742330710382L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leg() {
    }

    /**
     * 
     * @param startAddress
     * @param duration
     * @param distance
     * @param endLocation
     * @param startLocation
     * @param steps
     * @param endAddress
     */
    public Leg(Distance distance, Duration duration, String endAddress, EndLocation endLocation, String startAddress, StartLocation startLocation, List<Step> steps) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.endAddress = endAddress;
        this.endLocation = endLocation;
        this.startAddress = startAddress;
        this.startLocation = startLocation;
        this.steps = steps;
    }

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

}
