package fr.xebia.xke.step1;

import fr.xebia.xke.java7.domain.Appointment;
import fr.xebia.xke.java7.step1.CalendarManagement;
import fr.xebia.xke.java7.step1.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static fr.xebia.xke.test.Conditions.equivalentAs;
import static org.fest.assertions.api.Assertions.assertThat;

public class CalendarManagementTest {

    private CalendarManagement calendarManagement;

    private fr.xebia.xke.java8.step1.CalendarManagement calendarManagementJava8;

    @Before
    public void setUp() {
        calendarManagement = new CalendarManagement();

        calendarManagementJava8 = new fr.xebia.xke.java8.step1.CalendarManagement();
    }

    @Test
    public void should_create_appointment_for_one_day_java8() {
        String startDate = "2014-01-07";
        String title = "Xke";
        String description = "Xke de Janvier";

        String appointmentId = calendarManagement.createAppointmentForDay(startDate, title, description);
        Appointment appointmentCreated = calendarManagement.findAppointmentById(appointmentId);


        String appointmentIdJava8 = calendarManagementJava8.createAppointmentForDay(startDate, title, description);
        fr.xebia.xke.java8.domain.Appointment appointmentCreatedJava8 = calendarManagementJava8.findAppointmentById(appointmentIdJava8);

        assertThat(appointmentCreated).is(equivalentAs(appointmentCreatedJava8));


    }

    @Test
    public void should_create_appointment_java8() {
        String startDateTime = "2014-01-27T12:05:00.";
        String title = "Déjeuner";
        String description = "Déjeuner le midi avec mon manager";

        String appointmentId = calendarManagement.createAppointment(startDateTime, 2 * 60, title, description);
        Appointment appointmentCreated = calendarManagement.findAppointmentById(appointmentId);


        String appointmentIdJava8 = calendarManagementJava8.createAppointment(startDateTime, 2 * 60, title, description);
        fr.xebia.xke.java8.domain.Appointment appointmentCreatedJava8 = calendarManagementJava8.findAppointmentById(appointmentIdJava8);

        assertThat(appointmentCreated.toString()).isEqualTo(appointmentCreatedJava8.toString());
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

    @Test
    public void should_return_appointment_of_the_day() {
        addDataSet();

        List<Appointment> appointmentBetween = calendarManagement.findAppointmentOfDay(DateUtils.parseDate("2014-01-03"));

        assertThat(appointmentBetween).hasSize(2);

        assertThat(calendarManagement.findAppointmentOfDay(DateUtils.parseDate("2014-01-03")))
                .is(equivalentAs(calendarManagementJava8.findAppointmentOfDay(fr.xebia.xke.java8.step1.DateUtils.parseDate("2014-01-03"))));
    }

    @Test
    public void should_return_next_appointment_after() {
        addDataSet();

        Appointment appointment = calendarManagement.findNextAppointmentAfter(DateUtils.parseDateTime("2014-01-04T19:05:00."));

        assertThat(appointment.toString()).isEqualTo("Appointment{start=05/01/2014 01:05:00, end=05/01/2014 01:35:00, title='sujet', description='description', allTheDay='false'}");

        assertThat(calendarManagement.findNextAppointmentAfter(DateUtils.parseDateTime("2014-01-04T19:05:00.")))
                .is(equivalentAs(calendarManagementJava8.findNextAppointmentAfter(fr.xebia.xke.java8.step1.DateUtils.parseDateTime("2014-01-04T19:05:00."))));
    }

    private void addDataSet() {
        calendarManagement.createAppointment("2014-01-03T12:05:00.", 60, "sujet", "description");
        calendarManagement.createAppointment("2014-01-04T18:05:00.", 3 * 60, "sujet", "description");
        calendarManagement.createAppointment("2014-01-05T01:05:00.", 30, "sujet", "description");
        calendarManagement.createAppointment("2014-01-02T22:05:00.", 4 * 60, "sujet", "description");
        calendarManagement.createAppointmentForDay("2014-01-03", "sujet", "description");

        calendarManagementJava8.createAppointment("2014-01-03T12:05:00.", 60, "sujet", "description");
        calendarManagementJava8.createAppointment("2014-01-04T18:05:00.", 3 * 60, "sujet", "description");
        calendarManagementJava8.createAppointment("2014-01-05T01:05:00.", 30, "sujet", "description");
        calendarManagementJava8.createAppointment("2014-01-02T22:05:00.", 4 * 60, "sujet", "description");
        calendarManagementJava8.createAppointmentForDay("2014-01-03", "sujet", "description");

    }
}
