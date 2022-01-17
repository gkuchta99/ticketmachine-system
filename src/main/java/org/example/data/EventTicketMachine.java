package org.example.data;

public class EventTicketMachine {
    private int availableTickets;
    private String eventName;

    public EventTicketMachine() {
    }

    public EventTicketMachine(String eventName, int availableTickets) {
        this.eventName = eventName;
        this.availableTickets = availableTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
