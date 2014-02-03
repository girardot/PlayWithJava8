package fr.xebia.xke.java8.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Appointment {

    private static final DateTimeFormatter TO_STRING_PARSER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private LocalDateTime start, end;

    private String title, description;

    private String id;

    private boolean allTheDay;

    public Appointment(LocalDateTime start, LocalDateTime end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;

        this.id = UUID.randomUUID().toString();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isAllTheDay() {
        return allTheDay;
    }

    public void setAllTheDay(boolean allTheDay) {
        this.allTheDay = allTheDay;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "start=" + start.format(TO_STRING_PARSER) +
                ", end=" + end.format(TO_STRING_PARSER) +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", allTheDay='" + allTheDay + '\'' +
                '}';
    }

    public boolean between(LocalDateTime startDate, LocalDateTime endDate) {

        return (start.equals(startDate) || startDate.isBefore(start)) &&
                start.isBefore(endDate);
    }
}
