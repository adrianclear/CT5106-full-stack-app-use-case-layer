package ie.universityofgalway.cs.ct5106.studentreg.application.student.dto;

public record UpdateStudentCommand(String studentId, String newFirstName, String newLastName, String newEmail) {
}
