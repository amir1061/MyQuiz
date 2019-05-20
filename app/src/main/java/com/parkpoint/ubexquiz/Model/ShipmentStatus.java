package com.parkpoint.ubexquiz.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShipmentStatus {
    @SerializedName ("status")
    private String status;
    @SerializedName ("shipments")
    private List<Shipments>shipmentsList;

    public String getStatus() {
        return status;
    }

    public List <Shipments> getShipmentsList() {
        return shipmentsList;
    }
}
