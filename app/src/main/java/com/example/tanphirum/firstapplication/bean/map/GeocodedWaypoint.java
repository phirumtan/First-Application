
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodedWaypoint implements Serializable
{

    @SerializedName("geocoderStatus")
    @Expose
    public String geocoderStatus;
    @SerializedName("partialMatch")
    @Expose
    public boolean partialMatch;
    @SerializedName("placeId")
    @Expose
    public String placeId;
    @SerializedName("types")
    @Expose
    public List<String> types = null;
    private final static long serialVersionUID = -808973820630589911L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeocodedWaypoint() {
    }

    /**
     * 
     * @param geocoderStatus
     * @param placeId
     * @param types
     * @param partialMatch
     */
    public GeocodedWaypoint(String geocoderStatus, boolean partialMatch, String placeId, List<String> types) {
        super();
        this.geocoderStatus = geocoderStatus;
        this.partialMatch = partialMatch;
        this.placeId = placeId;
        this.types = types;
    }

    public GeocodedWaypoint withGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
        return this;
    }

    public GeocodedWaypoint withPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
        return this;
    }

    public GeocodedWaypoint withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public GeocodedWaypoint withTypes(List<String> types) {
        this.types = types;
        return this;
    }

}
