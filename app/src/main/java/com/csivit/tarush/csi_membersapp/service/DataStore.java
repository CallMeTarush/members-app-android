package com.csivit.tarush.csi_membersapp.service;


import com.csivit.tarush.csi_membersapp.model.system.User;

public class DataStore {
    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private String jwtToken;
    private User registeringUser;

    private DataStore() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public User getRegisteringUser() {
        return registeringUser;
    }

    public void setRegisteringUser(User registeringUser) {
        this.registeringUser = registeringUser;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
