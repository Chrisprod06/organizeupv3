package com.example.organizeupv3.models;

public class GroupMember {
    //Variable declaration
    private int groupID;
    private int memberID;

    //Empty constructor
    public GroupMember() {
        setGroupID(0);
        setMemberID(0);
    }

    //Full constructor
    public GroupMember(int gID, int mID) {
        setGroupID(gID);
        setMemberID(mID);
    }

    //Set functions
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    //Get functions
    public int getGroupID() {
        return groupID;
    }

    public int getMemberID() {
        return memberID;
    }

}
