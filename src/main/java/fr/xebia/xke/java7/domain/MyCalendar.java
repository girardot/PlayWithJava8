package fr.xebia.xke.java7.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyCalendar {

    private Map<String, Appointment> appointmentsById = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        appointmentsById.put(appointment.getId(), appointment);
    }

    public Appointment getById(String id) {
        return appointmentsById.get(id);
    }

    public Collection<Appointment> appointments() {
        return appointmentsById.values();
    }
}
