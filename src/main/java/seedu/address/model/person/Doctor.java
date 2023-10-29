package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    private final Set<Appointment> appointments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Address address, Remark remark, Gender gender,
                  Ic ic, Set<Tag> tags) {
        super(name, phone, email, address, remark, gender, ic, tags);
    }

    /**
     * Retrieves the list of patients stored in this medical facility.
     *
     * @return An ArrayList containing the patients currently registered in the facility.
     */
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Adds a new patient to the medical facility's list of patients.
     *
     * @param appointment The Patient object representing the individual to be added.
     */
    public Doctor addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        return this;
    }

    /**
     * Returns true if person is a doctor.
     */
    @Override
    public boolean isDoctor() {
        return true;
    }

    /**
     * Returns true if person is a patient.
     */
    @Override
    public boolean isPatient() {
        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return name.equals(otherDoctor.name)
                && phone.equals(otherDoctor.phone)
                && email.equals(otherDoctor.email)
                && address.equals(otherDoctor.address)
                && gender.equals(otherDoctor.gender)
                && ic.equals(otherDoctor.ic)
                && tags.equals(otherDoctor.tags)
                && appointments.equals(otherDoctor.appointments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, ic, tags, appointments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("gender", gender)
                .add("nric", ic)
                .add("appointments", appointments)
                .add("tags", tags)
                .toString();
    }

}

