
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration implements Serializable
{

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    public int value;
    private final static long serialVersionUID = 2913437190180636935L;

    public Duration withText(String text) {
        this.text = text;
        return this;
    }

    public Duration withValue(int value) {
        this.value = value;
        return this;
    }

}
