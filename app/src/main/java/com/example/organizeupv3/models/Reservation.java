package com.example.organizeupv3.models;

import java.sql.Date;
import java.sql.Time;

public class Reservation {

    //Variable declaration
    private int reservationID;
    private int groupID;
    private int placeID;
    private String date;
    private String time;
    private int numPeople;
    private String message;
    private String approval;

    //Empty constructor
    public Reservation(){
        setReservationID(0);
        setGroupID(0);
        setPlaceID(0);
        setDate("");
        setTime("");
        setMessage("");
        setApproval("");
    }
    //Full constructor
    public Reservation(int rID, int gID, int pID, String d, String t, int np, String m, String a){
        setReservationID(rID);
        setGroupID(gID);
        setPlaceID(pID);
        setDate(d);
        setTime(t);
        setMessage(m);
        setApproval(a);
    }

    //Set functions

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

}
