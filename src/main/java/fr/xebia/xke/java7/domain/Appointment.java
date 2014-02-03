package fr.xebia.xke.java7.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Appointment {

    private Date start, end;

    private String title, description;

    private String id;

    private boolean allTheDay;

    public Appointment(Date start, Date end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;

        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "Appointment{" +
                "start=" + simpleDateFormat.format(start) +
                ", end=" + simpleDateFormat.format(end) +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", allTheDay='" + allTheDay + '\'' +
                '}';
    }

    public boolean isAllTheDay() {
        return allTheDay;
    }

    public void setAllTheDay(boolean allTheDay) {
        this.allTheDay = allTheDay;
    }
}
