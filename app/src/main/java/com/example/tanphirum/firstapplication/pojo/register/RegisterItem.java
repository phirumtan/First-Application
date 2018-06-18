package com.example.tanphirum.firstapplication.pojo.register;

import com.example.tanphirum.firstapplication.pojo.BaseItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterItem extends BaseItem implements Serializable{

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    private final static long serialVersionUID = 9115137735872722905L;

    public RegisterItem(String token) {
        super(token);
    }

    @Override
    public BaseItem withToken(String token) {
        return super.withToken(token);
    }

    public RegisterItem withEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterItem withPassword(String password) {
        this.password = password;
        return this;
    }
}
