package com.example.organizeupv3.models;

import java.sql.Time;

public class Place {
    //Variable declaration
    private int placeID;
    private int ownerID;
    private String placeName;
    private int capacity;
    private String address;
    private String openTime;
    private String closeTime;
    private int phone;
    private String type;

    //Empty constructor
    public Place() {
        setPlaceID(0);
        setOwnerID(0);
        setPlaceName("");
        setCapacity(0);
        setAddress("");
        setOpenTime("");
        setCloseTime("");
        setPhone(0);
        setType("");
    }

    //Full constructor
    public Place(int pID, int oID, String pn, int c, String a, String ot, String ct, int p, String tp){
        setPlaceID(pID);
        setOwnerID(oID);
        setPlaceName(pn);
        setCapacity(c);
        setAddress(a);
        setOpenTime(ot);
        setCloseTime(ct);
        setPhone(p);
        setType(tp);

    }

    //Set functions

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Get functions

    public int getPlaceID() {
        return placeID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public int getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }
}