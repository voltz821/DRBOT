package com.example.drbot.Utills;

public class Doctors {
    private String dr_name,dr_speciality;

    public Doctors() {
    }

    public Doctors(String dr_name, String dr_speciality) {
        this.dr_name = dr_name;

        this.dr_speciality = dr_speciality;

    }

    public String getDr_name() {
        return dr_name;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }



    public String getDr_speciality() {
        return dr_speciality;
    }

    public void setDr_speciality(String dr_speciality) {
        this.dr_speciality = dr_speciality;
    }


}
