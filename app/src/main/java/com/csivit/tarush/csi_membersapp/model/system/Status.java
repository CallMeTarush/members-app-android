package com.csivit.tarush.csi_membersapp.model.system;


import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("success")
    private boolean statusSuccess;

    @SerializedName("code")
    private int statusCode;

    @SerializedName("message")
    private String statusMessage;


    public boolean isStatusSuccess() {
        return statusSuccess;
    }

    public void setStatusSuccess(boolean statusSuccess) {
        this.statusSuccess = statusSuccess;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
