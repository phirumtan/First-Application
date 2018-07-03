
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration implements Serializable
{

    @SerializedName("humanReadable")
    @Expose
    public String humanReadable;
    @SerializedName("inSeconds")
    @Expose
    public int inSeconds;
    private final static long serialVersionUID = -167146736247432225L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Duration() {
    }

    /**
     * 
     * @param inSeconds
     * @param humanReadable
     */
    public Duration(String humanReadable, int inSeconds) {
        super();
        this.humanReadable = humanReadable;
        this.inSeconds = inSeconds;
    }

    public Duration withHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
        return this;
    }

    public Duration withInSeconds(int inSeconds) {
        this.inSeconds = inSeconds;
        return this;
    }

}
