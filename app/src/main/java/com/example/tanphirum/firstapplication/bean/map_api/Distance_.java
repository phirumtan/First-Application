
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance_ implements Serializable
{

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    public int value;
    private final static long serialVersionUID = 1339670819382328813L;

    public Distance_ withText(String text) {
        this.text = text;
        return this;
    }

    public Distance_ withValue(int value) {
        this.value = value;
        return this;
    }

}
