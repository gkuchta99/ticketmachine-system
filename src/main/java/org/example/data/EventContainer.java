package org.example.data;

import java.util.ArrayList;

public class EventContainer {
    public ArrayList<Event> eventArrayList = new ArrayList<>();

    public void addEvent(String name, int tickets) {
        if (!eventArrayList.isEmpty()) {
            for (Event event : eventArrayList) {
                if (event.getEventName().equals(name)) {
                    event.setTotalTickets(event.getTotalTickets() + tickets);
                    return;
                }
            }
        }
        eventArrayList.add(new Event(tickets, name));
    }
}
