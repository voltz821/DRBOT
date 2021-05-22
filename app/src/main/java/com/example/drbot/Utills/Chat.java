package com.example.drbot.Utills;

public class Chat {


    private String sms,userID;

    public Chat() {
    }

    public Chat(String sms, String userID) {
        this.sms = sms;
        this.userID = userID;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
