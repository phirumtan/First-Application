
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Serializable
{

    @SerializedName("bounds")
    @Expose
    public Bounds bounds;
    @SerializedName("copyrights")
    @Expose
    public String copyrights;
    @SerializedName("legs")
    @Expose
    public List<Leg> legs = null;
    @SerializedName("overviewPolyline")
    @Expose
    public OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("warnings")
    @Expose
    public List<Object> warnings = null;
    @SerializedName("waypointOrder")
    @Expose
    public List<Object> waypointOrder = null;
    private final static long serialVersionUID = -4489042507860149176L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Route() {
    }

    /**
     * 
     * @param waypointOrder
     * @param summary
     * @param bounds
     * @param copyrights
     * @param legs
     * @param warnings
     * @param overviewPolyline
     */
    public Route(Bounds bounds, String copyrights, List<Leg> legs, OverviewPolyline overviewPolyline, String summary, List<Object> warnings, List<Object> waypointOrder) {
        super();
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.legs = legs;
        this.overviewPolyline = overviewPolyline;
        this.summary = summary;
        this.warnings = warnings;
        this.waypointOrder = waypointOrder;
    }

    public Route withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public Route withCopyrights(String copyrights) {
        this.copyrights = copyrights;
        return this;
    }

    public Route withLegs(List<Leg> legs) {
        this.legs = legs;
        return this;
    }

    public Route withOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
        return this;
    }

    public Route withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Route withWarnings(List<Object> warnings) {
        this.warnings = warnings;
        return this;
    }

    public Route withWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
        return this;
    }

}
