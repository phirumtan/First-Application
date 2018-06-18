package com.example.tanphirum.firstapplication.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseItem implements Serializable{

    @SerializedName("token")
    @Expose
    public String token;

    public BaseItem(String token) {
        this.token = token;
    }

    public BaseItem withToken(String token) {
        this.token = token;
        return this;
    }
}
