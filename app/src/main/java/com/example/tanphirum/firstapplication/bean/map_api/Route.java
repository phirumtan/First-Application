
package com.example.tanphirum.firstapplication.bean.map_api;

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
    @SerializedName("overview_polyline")
    @Expose
    public OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("warnings")
    @Expose
    public List<Object> warnings = null;
    @SerializedName("waypoint_order")
    @Expose
    public List<Integer> waypointOrder = null;
    private final static long serialVersionUID = -7277901371566550148L;

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

    public Route withWaypointOrder(List<Integer> waypointOrder) {
        this.waypointOrder = waypointOrder;
        return this;
    }

}
