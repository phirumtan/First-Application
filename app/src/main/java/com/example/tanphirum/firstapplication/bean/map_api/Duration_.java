
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration_ implements Serializable
{

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    public int value;
    private final static long serialVersionUID = 6794336104594927947L;

    public Duration_ withText(String text) {
        this.text = text;
        return this;
    }

    public Duration_ withValue(int value) {
        this.value = value;
        return this;
    }

}
