
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance_ implements Serializable
{

    @SerializedName("humanReadable")
    @Expose
    public String humanReadable;
    @SerializedName("inMeters")
    @Expose
    public int inMeters;
    private final static long serialVersionUID = 1040552854890022050L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Distance_() {
    }

    /**
     * 
     * @param humanReadable
     * @param inMeters
     */
    public Distance_(String humanReadable, int inMeters) {
        super();
        this.humanReadable = humanReadable;
        this.inMeters = inMeters;
    }

    public Distance_ withHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
        return this;
    }

    public Distance_ withInMeters(int inMeters) {
        this.inMeters = inMeters;
        return this;
    }

}
