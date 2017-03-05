package com.csivit.tarush.csi_membersapp.model.response;


import com.csivit.tarush.csi_membersapp.model.system.Event;
import com.csivit.tarush.csi_membersapp.model.system.Status;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  EventsResponse {

    @SerializedName("status")
    private Status status;

    @SerializedName("Events")
    private List<Event> events;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
