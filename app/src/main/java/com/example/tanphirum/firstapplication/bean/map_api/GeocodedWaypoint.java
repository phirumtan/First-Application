
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodedWaypoint implements Serializable
{

    @SerializedName("geocoder_status")
    @Expose
    public String geocoderStatus;
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("types")
    @Expose
    public List<String> types = null;
    private final static long serialVersionUID = 2630166093775168108L;

    public GeocodedWaypoint withGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
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
