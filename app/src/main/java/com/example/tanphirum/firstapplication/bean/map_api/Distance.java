
package com.example.tanphirum.firstapplication.bean.map_api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance implements Serializable
{

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    public int value;
    private final static long serialVersionUID = -1613213509917353623L;

    public Distance withText(String text) {
        this.text = text;
        return this;
    }

    public Distance withValue(int value) {
        this.value = value;
        return this;
    }

}
