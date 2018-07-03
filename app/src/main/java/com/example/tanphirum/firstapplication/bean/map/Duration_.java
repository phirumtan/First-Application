
package com.example.tanphirum.firstapplication.bean.map;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration_ implements Serializable
{

    @SerializedName("humanReadable")
    @Expose
    public String humanReadable;
    @SerializedName("inSeconds")
    @Expose
    public int inSeconds;
    private final static long serialVersionUID = -251665512177906997L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Duration_() {
    }

    /**
     * 
     * @param inSeconds
     * @param humanReadable
     */
    public Duration_(String humanReadable, int inSeconds) {
        super();
        this.humanReadable = humanReadable;
        this.inSeconds = inSeconds;
    }

    public Duration_ withHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
        return this;
    }

    public Duration_ withInSeconds(int inSeconds) {
        this.inSeconds = inSeconds;
        return this;
    }

}
