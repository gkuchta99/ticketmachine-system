package org.example.data;

import java.util.ArrayList;

public class Event {
    private ArrayList<String> participantsArrayList;
    private int totalTickets;
    private String eventName;

    public Event(int totalTickets, String eventName) {
        this.totalTickets = totalTickets;
        this.eventName = eventName;
        this.participantsArrayList = new ArrayList<>();
    }

    public ArrayList<String> getParticipantsArrayList() {
        return participantsArrayList;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setParticipantsArrayList(ArrayList<String> participantsArrayList) {
        this.participantsArrayList = participantsArrayList;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}
