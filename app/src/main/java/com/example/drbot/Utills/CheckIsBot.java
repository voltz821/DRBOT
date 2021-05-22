package com.example.drbot.Utills;

public class CheckIsBot {

    String textmessage;
    boolean isme;

    public CheckIsBot(String textmessage, boolean isme) {
        this.textmessage = textmessage;
        this.isme = isme;
    }

    public String getTextmessage() {
        return textmessage;
    }

    public void setTextmessage(String textmessage) {
        this.textmessage = textmessage;
    }

    public boolean isIsme() {
        return isme;
    }

    public void setIsme(boolean isme) {
        this.isme = isme;
    }
}
