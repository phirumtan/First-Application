
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapItem implements Serializable
{

    @SerializedName("geocoded_waypoints")
    @Expose
    public List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    public List<Route> routes = null;
    @SerializedName("status")
    @Expose
    public String status;
    private final static long serialVersionUID = 2566122597830939796L;

    public MapItem withGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
        return this;
    }

    public MapItem withRoutes(List<Route> routes) {
        this.routes = routes;
        return this;
    }

    public MapItem withStatus(String status) {
        this.status = status;
        return this;
    }

}
