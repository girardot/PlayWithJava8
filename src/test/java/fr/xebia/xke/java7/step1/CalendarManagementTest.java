package fr.xebia.xke.java7.step1;

import fr.xebia.xke.java7.domain.Appointment;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class CalendarManagementTest {

    private CalendarManagement calendarManagement;

    @Before
    public void setUp() {
        calendarManagement = new CalendarManagement();
    }

    @Test
    public void should_create_appointment_for_one_day() {
        String appointmentId = calendarManagement.createAppointmentForDay("2014-01-07", "Xke", "Xke de Janvier");

        Appointment appointmentCreated = calendarManagement.findAppointmentById(appointmentId);

        assertThat(appointmentCreated).isNotNull();
        assertThat(appointmentCreated.getStart()).isInSameDayAs("2014-01-07");
        assertThat(appointmentCreated.getStart()).isWithinHourOfDay(0);
        assertThat(appointmentCreated.getStart()).isWithinMinute(0);
        assertThat(appointmentCreated.getStart()).isWithinSecond(0);

        assertThat(appointmentCreated.getEnd()).isInSameDayAs("2014-01-07");
        assertThat(appointmentCreated.getEnd()).isWithinHourOfDay(23);
        assertThat(appointmentCreated.getEnd()).isWithinMinute(59);
        assertThat(appointmentCreated.getEnd()).isWithinSecond(59);

        assertThat(appointmentCreated.getTitle()).isEqualTo("Xke");
        assertThat(appointmentCreated.getDescription()).isEqualTo("Xke de Janvier");
    }

    @Test
    public void should_create_appointment() {
        String appointmentId = calendarManagement.createAppointment("2014-01-27T12:05:00.", 2 * 60, "Déjeuner", "Déjeuner le midi avec mon manager");

        Appointment appointmentCreated = calendarManagement.findAppointmentById(appointmentId);

        assertThat(appointmentCreated).isNotNull();

        assertThat(appointmentCreated.getStart()).isInSameDayAs("2014-01-27");
        assertThat(appointmentCreated.getStart()).isWithinHourOfDay(12);
        assertThat(appointmentCreated.getStart()).isWithinMinute(05);
        assertThat(appointmentCreated.getStart()).isWithinSecond(0);

        assertThat(appointmentCreated.getEnd()).isInSameDayAs("2014-01-27");
        assertThat(appointmentCreated.getEnd()).isWithinHourOfDay(14);
        assertThat(appointmentCreated.getEnd()).isWithinMinute(05);
        assertThat(appointmentCreated.getEnd()).isWithinSecond(0);

        assertThat(appointmentCreated.getTitle()).isEqualTo("Déjeuner");
        assertThat(appointmentCreated.getDescription()).isEqualTo("Déjeuner le midi avec mon manager");
    }
}
