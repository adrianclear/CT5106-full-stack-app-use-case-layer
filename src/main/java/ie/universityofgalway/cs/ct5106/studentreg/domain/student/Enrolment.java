package ie.universityofgalway.cs.ct5106.studentreg.domain.student;

import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;

import java.time.Instant;
import java.util.Objects;

public class Enrolment {
    private final EnrolmentId id;
    private final CourseId courseId;
    private final Instant enrolledAt;
    private EnrolmentStatus status;

    //Private constructor to enforce creation through factory method
    //Otherwise, we get tight coupling to the constructor (callers need to know how to build it).
    //    The caller must know:
    //    -that an ID must be generated,
    //    -that enrolledAt should be now(),
    //    -that the initial status must be ENROLLED.
    // And a risk of invalid construction e.g. forgetting to set status.
    // constructor is about how to instantiate an object, not about the domain rules.
    //Factory method: Instead of calling new directly, you ask a factory method (create(...)) to give you an instance.
    private Enrolment(EnrolmentId id, CourseId courseId, Instant enrolledAt, EnrolmentStatus status) {
        this.id = id;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
        this.status = status;
    }

    //Package-private factory method so that only student aggregate root can create enrolments
    //Only the aggregate root should expose operations that change the aggregate’s state.
    //Method is intention-revealing: it shows how to create a valid enrolment.
    //Factory method is about the domain rules (how to create something valid in the domain), not about how to instantiate an object.
    static Enrolment create(CourseId courseId) {
        return new Enrolment(EnrolmentId.random(), courseId, Instant.now(), EnrolmentStatus.ENROLLED);
    }

    //Methods with read-only behaivour - they don’t change state.
    //For Enrolments, the outside world may need to display them, log them, or check equality.
    //Read-only methods don’t undermine the aggregate’s rules.
    public EnrolmentId id() {
        return id;
    }

    public CourseId courseId() {
        return courseId;
    }

    public Instant enrolledAt() {
        return enrolledAt;
    }

    public EnrolmentStatus status() {
        return status;
    }

    // Domain behavior: status transitions (invariant-changing methods)
    //everything in an aggregate lives in the same package, so you can use package-private to encapsulate
    //Only the aggregate root should expose operations that change the aggregate’s state.
    //Invariant-changing methods are methods that change the state of the aggregate in a way that affects its invariants.
    void withdraw() {
        if (status != EnrolmentStatus.ENROLLED) {
            throw new IllegalStateException("Only ENROLLED students can withdraw.");
        }
        this.status = EnrolmentStatus.WITHDRAWN;
    }

    //everything in an aggregate lives in the same package, so you can use package-private to encapsulate
    void complete() {
        if (status != EnrolmentStatus.ENROLLED) {
            throw new IllegalStateException("Only ENROLLED students can be marked completed.");
        }
        this.status = EnrolmentStatus.COMPLETED;
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrolment)) return false;
        Enrolment other = (Enrolment) o;
        return id.equals(other.id);
    }

    @Override public int hashCode() {
        return Objects.hash(id);
    }

    @Override public String toString() {
        return "Enrolment{" + id + ", course=" + courseId + ", status=" + status + ", at=" + enrolledAt + "}";
    }
}
