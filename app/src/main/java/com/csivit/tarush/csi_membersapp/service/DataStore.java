package com.csivit.tarush.csi_membersapp.service;


public class DataStore {
    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private String jwtToken;

    private DataStore() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
