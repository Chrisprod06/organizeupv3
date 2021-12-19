package com.example.organizeupv3.models;

public class Group {

    //Variable declaration
    private int groupID;
    private String groupName;
    private int creatorID;

    //Empty constructor
    public Group(){
        setGroupID(0);
        setGroupName("Name");
        setCreatorID(0);
    }

    //Full constructor
    public Group( int gID, String gn, int cID){
        setGroupID(gID);
        setGroupName(gn);
        setCreatorID(cID);
    }

    //Set functions
    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    //Get functions
    public int getCreatorID() {
        return creatorID;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }
}
