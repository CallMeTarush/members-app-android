package com.csivit.tarush.csi_membersapp.model.response;


import com.csivit.tarush.csi_membersapp.model.system.Status;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {

    @SerializedName("name")
    private String userDisplayName;

    @SerializedName("username")
    private String userRegNo;

    @SerializedName("phone")
    private String userPhoneNo;

    @SerializedName("mem_type")
    private String userMemberType;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("status")
    private Status status;

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserRegNo() {
        return userRegNo;
    }

    public void setUserRegNo(String userRegNo) {
        this.userRegNo = userRegNo;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserMemberType() {
        return userMemberType;
    }

    public void setUserMemberType(String userMemberType) {
        this.userMemberType = userMemberType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
