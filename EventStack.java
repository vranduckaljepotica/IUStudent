package ius.iustudent.models;

public class EventStack {
    private Event[] events;
    public EventStack(Event monday, Event tuesday, Event wednesday, Event thursday, Event friday, Event saturday){
        events = new Event[6];
        events[0] = monday;
        events[1] = tuesday;
        events[2] = wednesday;
        events[3] = thursday;
        events[4] = friday;
        events[5] = saturday;
    }

    public EventStack(Event[] events) {
        this.events = events;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
