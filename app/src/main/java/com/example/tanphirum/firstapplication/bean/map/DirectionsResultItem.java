
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectionsResultItem implements Serializable
{

    @SerializedName("geocodedWaypoints")
    @Expose
    public List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    public List<Route> routes = null;
    private final static long serialVersionUID = -1229886776635894537L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DirectionsResultItem() {
    }

    /**
     * 
     * @param routes
     * @param geocodedWaypoints
     */
    public DirectionsResultItem(List<GeocodedWaypoint> geocodedWaypoints, List<Route> routes) {
        super();
        this.geocodedWaypoints = geocodedWaypoints;
        this.routes = routes;
    }

    public DirectionsResultItem withGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
        return this;
    }

    public DirectionsResultItem withRoutes(List<Route> routes) {
        this.routes = routes;
        return this;
    }

}
