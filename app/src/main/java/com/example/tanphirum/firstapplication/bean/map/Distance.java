
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance implements Serializable
{

    @SerializedName("humanReadable")
    @Expose
    public String humanReadable;
    @SerializedName("inMeters")
    @Expose
    public int inMeters;
    private final static long serialVersionUID = 2015286899388408344L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Distance() {
    }

    /**
     * 
     * @param humanReadable
     * @param inMeters
     */
    public Distance(String humanReadable, int inMeters) {
        super();
        this.humanReadable = humanReadable;
        this.inMeters = inMeters;
    }

    public Distance withHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
        return this;
    }

    public Distance withInMeters(int inMeters) {
        this.inMeters = inMeters;
        return this;
    }

}
