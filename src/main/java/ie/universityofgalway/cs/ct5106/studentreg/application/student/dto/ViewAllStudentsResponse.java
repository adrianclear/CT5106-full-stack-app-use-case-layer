package ie.universityofgalway.cs.ct5106.studentreg.application.student.dto;

import java.util.List;

public record ViewAllStudentsResponse(List<StudentSummary> students) {
    public record StudentSummary(String id, String name, String email) {}
}
