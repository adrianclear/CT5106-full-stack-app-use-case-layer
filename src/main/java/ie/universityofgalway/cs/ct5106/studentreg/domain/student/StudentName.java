package ie.universityofgalway.cs.ct5106.studentreg.domain.student;

import java.util.Objects;

public class StudentName {

    private final String firstName;
    private final String lastName;

    private StudentName(String firstName, String lastName) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public static StudentName of(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name required");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name required");

        return new StudentName(firstName, lastName);
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentName)) return false;
        StudentName other = (StudentName)o;

        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }
    @Override public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override public String toString() {
        return fullName();
    }

}
