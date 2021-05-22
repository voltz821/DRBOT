package com.example.drbot.Utills;

import com.firebase.ui.auth.data.model.User;

public class users {
    private String username,usersurname;

    public users() {
    }

    public users(String username, String usersurname) {
        this.username = username;

        this.usersurname = usersurname;
    }

    public String getusername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getusersurname() {
        return usersurname;
    }

    public void setusersurname(String usersurname) {
        this.usersurname = usersurname;
    }

}
