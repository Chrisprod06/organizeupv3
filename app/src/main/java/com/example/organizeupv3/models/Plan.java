package com.example.organizeupv3.models;

public class Plan {
    //Model variables
    private int planID;
    private int groupID;
    private int placeID;
    private String time;
    private String date;
    private String message;

    //Empty constructor
    public Plan() {
        setplanID(0);
        setGroupID(0);
        setPlaceID(0);
        setTime("00:00:00");
        setDate("1/1/2021");
        setMessage("Message");
    }

    //Full constructor
    public Plan(int aID, int gID, int pID, String t, String d, String m){
        setplanID(aID);
        setGroupID(gID);
        setPlaceID(pID);
        setTime(t);
        setDate(d);
        setMessage(m);
    }
    //Set functions
    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String s) {
        this.date = s;
    }

    public void setTime(String i) {
        this.time = i;
    }

    public void setPlaceID(int i) {
        this.placeID = i;
    }

    public void setGroupID(int i) {
        this.groupID = i;
    }

    public void setplanID(int i) {
        this.planID = i;
    }

    //Get functions
    public int getPlanID(){
        return planID;
    }
    public  int getGroupID(){
        return  groupID;
    }
    public int getPlaceID(){
        return  placeID;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
