package ie.universityofgalway.cs.ct5106.studentreg.application.student.dto;

import java.util.List;

public record StudentCoursesResponse(List<StudentCourseDTO> courses) {
    public record StudentCourseDTO(String courseId, String status) {}
}
