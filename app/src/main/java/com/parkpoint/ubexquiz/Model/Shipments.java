package com.parkpoint.ubexquiz.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shipments {
@SerializedName ("reference")
@Expose
private String reference;
@SerializedName ("from")
@Expose
private String from;
@SerializedName ("status")
@Expose
private String status;
@SerializedName ("created_at")
@Expose
private String created_at;
@SerializedName ("deliver_at")
@Expose
private String delivered;
@SerializedName ("location")
@Expose
private String location;
@SerializedName ("price")
@Expose
private String price;

    public String getReference() {
        return reference;
    }

    public String getFrom() {
        return from;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDelivered() {
        return delivered;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }
}
