package com.dmy.mynavigation;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {
    String userName;
    String password;
    String gender;
    List<String> languages;

    public UserData(String userName, String password, String gender, List<String> languages) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.languages = languages;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
