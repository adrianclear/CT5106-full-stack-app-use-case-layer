package ie.universityofgalway.cs.ct5106.studentreg.domain.student;

import java.util.Objects;
import java.util.UUID;

public final class StudentId {
    private final UUID value;

    private StudentId(UUID value) {
        this.value = value;
    }

    public static StudentId of(UUID id) {
        return new StudentId(id);
    }

    public static StudentId random() {
        return new StudentId(UUID.randomUUID());
    }

    public UUID asUuid() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentId)) return false;
        StudentId other = (StudentId) o;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override public String toString() {
        return value.toString();
    }
}
