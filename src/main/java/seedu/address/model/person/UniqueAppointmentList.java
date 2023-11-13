package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.exceptions.DuplicateObjectException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.ObjectNotFoundException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique if it is not already present in the list, based on the equality defined by
 * {@code Appointment#equals(Object)}. As such, adding and updating of appointments use {@code Appointment#equals(Object)}
 * for equality to ensure that the appointment being added or updated is unique in terms of identity in the
 * UniqueAppointmentList. However, the removal of an appointment uses {@code Appointment#equals(Object)} as well,
 * so as to ensure that the appointment with exactly the same fields will be removed.
 * <p>
 * This class supports a minimal set of list operations and extends {@code UniqueObjectList}.
 *
 * @see Appointment#equals(Object)
 */
public class UniqueAppointmentList extends UniqueObjectList<Appointment> {

    protected final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns true if the list contains an equivalent {@code Appointment} as the given argument.
     */
    @Override
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds the specified {@code Appointment} to the list.
     *
     * @param toAdd The appointment to be added.
     * @throws NullPointerException     if {@code toAdd} is null.
     * @throws DuplicateObjectException if the appointment already exists in the list.
     */
    @Override
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateObjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the {@code target} appointment in the list with {@code editedAppointment}.
     * The {@code target} appointment must exist in the list.
     *
     * @param target           The appointment to be replaced.
     * @param editedAppointment The edited appointment to replace the target.
     * @throws NullPointerException       if {@code target} or {@code editedAppointment} is null.
     * @throws ObjectNotFoundException    if {@code target} does not exist in the list.
     * @throws DuplicateObjectException    if {@code editedAppointment} already exists in the list.
     */
    @Override
    public void setObject(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ObjectNotFoundException();
        }

        if (!target.equals(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent {@code Appointment} from the list.
     * The {@code Appointment} must exist in the list.
     *
     * @param toRemove The appointment to be removed.
     * @throws NullPointerException     if {@code toRemove} is null.
     * @throws ObjectNotFoundException  if {@code toRemove} does not exist in the list.
     */
    @Override
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ObjectNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with the provided list of {@code appointments}.
     * The provided list must not contain duplicate appointments.
     *
     * @param appointments The list of appointments to replace the current contents of this list.
     * @throws NullPointerException     if {@code appointments} is null.
     * @throws DuplicateObjectException if the provided list contains duplicate appointments.
     */
    @Override
    public void setObjects(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!objectsAreUnique(appointments)) {
            throw new DuplicateObjectException();
        }

        internalList.setAll(appointments);
    }

    @Override
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if the provided list of appointments contains only unique appointments.
     *
     * @param appointments The list of appointments to check for uniqueness.
     * @return True if all appointments in the list are unique, false otherwise.
     */
    @Override
    protected boolean objectsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
