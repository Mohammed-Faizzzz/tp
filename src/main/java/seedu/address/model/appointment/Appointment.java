package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Ic;

/**
 * The {@code Appointment} class represents a scheduled appointment between a doctor and a patient.
 * It includes information about the doctor, patient, and the appointment time.
 * Doctor and patient ic should not be the same.
 */
public class Appointment {
    private Ic doctorIc;
    private Ic patientIc;
    private AppointmentTime appointmentTime;
    private String status = "Scheduled";

    /**
     * Constructs a new appointment with the specified doctor, patient, and appointment time.
     *
     * @param doctorIc        The doctor involved in the appointment.
     * @param patientIc       The patient involved in the appointment.
     * @param appointmentTime The date and time of the appointment.
     */
    public Appointment(Ic doctorIc, Ic patientIc, AppointmentTime appointmentTime) {
        requireNonNull(doctorIc);
        requireNonNull(patientIc);
        requireNonNull(appointmentTime);
        this.doctorIc = doctorIc;
        this.patientIc = patientIc;
        this.appointmentTime = appointmentTime;
    }

    /**
     * Constructs a new appointment with the specified doctor, patient, appointment time, and status.
     *
     * @param doctorIc        The doctor involved in the appointment.
     * @param patientIc       The patient involved in the appointment.
     * @param appointmentTime The date and time of the appointment.
     * @param status          The status of the appointment.
     */
    public Appointment(Ic doctorIc, Ic patientIc, AppointmentTime appointmentTime, String status) {
        requireNonNull(doctorIc);
        requireNonNull(patientIc);
        requireNonNull(appointmentTime);
        requireNonNull(status);
        this.doctorIc = doctorIc;
        this.patientIc = patientIc;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }

    public Ic getDoctor() {
        return doctorIc;
    }

    public Ic getPatient() {
        return patientIc;
    }

    public String getStatus() {
        return status;
    }

    public void setAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void changeDoctor(Ic newDoctorIc) {
        this.doctorIc = newDoctorIc;
    }

    public void changePatient(Ic newPatientIc) {
        this.patientIc = newPatientIc;
    }

    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return this.doctorIc.equals(otherAppointment.doctorIc)
                && this.patientIc.equals(otherAppointment.patientIc)
                && this.appointmentTime.equals(otherAppointment.appointmentTime)
                && this.status.equals(otherAppointment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorIc, patientIc, appointmentTime, status);
    }

    @Override
    public String toString() {
        return "Patient with IC " + patientIc + ", Doctor with IC " + doctorIc + " at "
                + appointmentTime.toString();
    }
}
