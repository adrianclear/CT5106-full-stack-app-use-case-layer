package ie.universityofgalway.cs.ct5106.studentreg.domain.student;

import java.util.Objects;
import java.util.UUID;

public final class EnrolmentId {
    private final UUID value;

    private EnrolmentId(UUID value) {
        this.value = value;
    }
    // Factory method for creating a new random EnrolmentId
    // Used when creating a brand new Enrolment that does not yet exist in the system
    public static EnrolmentId random() {
        return new EnrolmentId(UUID.randomUUID());
    }


    // Factory method for creating a new EnrollmentId from an existing UUID
    // Used when reconstituting an existing Enrolment from a database or other storage
    public static EnrolmentId of(UUID id) {
        return new EnrolmentId(id);
    }

    public UUID asUuid() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrolmentId)) return false;
        EnrolmentId other = (EnrolmentId) o;
        return value.equals(other.value);
    }
    @Override public int hashCode() {
        return Objects.hash(value);
    }

    @Override public String toString() {
        return value.toString();
    }
}
