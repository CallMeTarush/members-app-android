package com.csivit.tarush.csi_membersapp.model.response;

import com.csivit.tarush.csi_membersapp.model.system.Status;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kushagra on 2/2/17.
 */

public class AuthResponse {
    @SerializedName("token")
    private String userToken;

    @SerializedName("status")
    private Status status;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
