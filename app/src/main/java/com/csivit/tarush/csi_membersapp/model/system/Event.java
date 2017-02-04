package com.csivit.tarush.csi_membersapp.model.system;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {

    @SerializedName("id")
    private int eventId;

    @SerializedName("date")
    private Date eventDate;

    @SerializedName("name")
    private String eventName;

    @SerializedName("venue")
    private String eventVenue;

    @SerializedName("cat")
    private String eventCategory;

    @SerializedName("link")
    private String eventLink;

    @SerializedName("short_desc")
    private String eventDescriptionShort;

    @SerializedName("time")
    private String eventTime;

    @SerializedName("desc")
    private String eventDescription;

    @SerializedName("images")
    private String eventImage;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getEventDescriptionShort() {
        return eventDescriptionShort;
    }

    public void setEventDescriptionShort(String eventDescriptionShort) {
        this.eventDescriptionShort = eventDescriptionShort;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
