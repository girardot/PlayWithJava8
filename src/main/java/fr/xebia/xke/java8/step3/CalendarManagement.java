package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.domain.Appointment;
import fr.xebia.xke.java8.domain.MyCalendar;
import fr.xebia.xke.java8.step2.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarManagement {

    private MyCalendar calendar;

    public CalendarManagement() {
        calendar = new MyCalendar();
    }

    public String createAppointment(String startDate, int durationInMinute, String title, String description) {
        LocalDateTime start = DateUtils.parseDateTime(startDate);
        LocalDateTime end = DateUtils.addDuration(start, durationInMinute);

        return createAppointment(title, description, start, end).getId();

    }

    public String createAppointmentForDay(String date, String title, String description) {
        LocalDate dayDate = DateUtils.parseDate(date);

        Appointment appointment = createAppointment(title, description, dayDate.atStartOfDay(), dayDate.atTime(23, 59, 59));
        appointment.setAllTheDay(true);

        return appointment.getId();

    }

    public Appointment findAppointmentById(String id) {
        return calendar.getById(id);
    }

    public List<Appointment> findAppointmentOfDay(LocalDate dayDate) {

        return calendar.appointments().stream()
                .filter(a -> a.getStart().toLocalDate().equals(dayDate))
                .sorted((a1, a2) -> a1.getStart().compareTo(a2.getStart()))
                .collect(Collectors.toList());

    }

    public Appointment findNextAppointmentAfter(LocalDateTime dateTime) {
        return calendar.appointments().stream()
                .sorted((a1, a2) -> a1.getStart().compareTo(a2.getStart()))
                .filter(a -> a.getStart().isAfter(dateTime))
                .findFirst()
                .orElse(null);
    }

    private Appointment createAppointment(String title, String description, LocalDateTime start, LocalDateTime end) {
        Appointment appointment = new Appointment(start, end, title, description);

        calendar.addAppointment(appointment);

        return appointment;
    }
}
