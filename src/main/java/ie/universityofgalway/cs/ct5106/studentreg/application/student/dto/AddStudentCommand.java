package ie.universityofgalway.cs.ct5106.studentreg.application.student.dto;

public record AddStudentCommand(String firstName, String lastName, String email) {
    public String firstName() {
        return firstName;
    }
    public String lastName() {
        return lastName;
    }
    public String email() {
        return email;
    }
}
