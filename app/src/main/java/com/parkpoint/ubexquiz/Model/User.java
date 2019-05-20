package com.parkpoint.ubexquiz.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    private String Resonse ;

    public String getToken() {
        return token;
    }

    @SerializedName("token")
    private String token ;
    @SerializedName("username")
    private String ID ;
    @SerializedName("password")
    private String Name ;

    public String getResonse() {
        return Resonse;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

}
