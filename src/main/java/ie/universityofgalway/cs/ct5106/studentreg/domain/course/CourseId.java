package ie.universityofgalway.cs.ct5106.studentreg.domain.course;

import java.util.Objects;
import java.util.UUID;

public final class CourseId {
    private final UUID value;

    private CourseId(UUID value) {
        this.value = value;
    }

    public static CourseId of(UUID uuid) {
        return new CourseId(uuid);
    }

    public static CourseId random() {
        return new CourseId(UUID.randomUUID());
    }

    public UUID asUuid() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseId)) return false;

        return value.equals(((CourseId)o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
