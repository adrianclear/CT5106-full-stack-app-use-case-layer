package ie.universityofgalway.cs.ct5106.studentreg.application.common.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
