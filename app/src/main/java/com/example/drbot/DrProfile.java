package com.example.drbot;

public class DrProfile {
    //Doctors Details
    public String dr_name;
    public String dr_email;
    public String dr_phone;
    public String dr_password;
    public String dr_speciality;

    public DrProfile(String name, String email, String phno, String password, String specialist) {
        this.dr_name = name;
        this.dr_email = email;
        this.dr_phone = phno;
        this.dr_password = password;
        this.dr_speciality = specialist;

    }

}
