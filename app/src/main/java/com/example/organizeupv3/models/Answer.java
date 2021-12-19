package com.example.organizeupv3.models;

public class Answer {
    //Variable declaration
    private int planID;
    private int groupID;
    private int memberID;
    private String answer;

    //Empty constructor
    public Answer(){
        setPlanID(0);
        setGroupID(0);
        setMemberID(0);
        setAnswer("answer");
    }

    //Full constructor
    public Answer(int aID, int gID, int mID, String a){
        setPlanID(aID);
        setGroupID(gID);
        setMemberID(mID);
        setAnswer(a);
    }

    //Set functions
    public void setPlanID(int activityID) {
        this.planID = activityID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
