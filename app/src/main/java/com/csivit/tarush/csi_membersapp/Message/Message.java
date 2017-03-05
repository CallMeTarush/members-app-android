package com.csivit.tarush.csi_membersapp.Message;

/**
 * Created by Mayank on 10-02-2017.
 */

public class Message {
    private String message;
    private boolean position;

    public Message(String mes, boolean pos) {
        message = mes;
        position = pos;
    }

    public String getMessage() {
        return message;
    }

    public boolean getPosition() {
        return position;
    }
}
