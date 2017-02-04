package com.csivit.tarush.csi_membersapp.model.system;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kushagra on 4/2/17.
 */

public class User {

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

    @SerializedName("key")
    private String userKey;

    @SerializedName("password")
    private String userPassword;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public User() {
    }


    public User(String userDisplayName, String userRegNo, String userPhoneNo, String userEmail, String userPassword) {
        this.userDisplayName = userDisplayName;
        this.userRegNo = userRegNo;
        this.userPhoneNo = userPhoneNo;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
