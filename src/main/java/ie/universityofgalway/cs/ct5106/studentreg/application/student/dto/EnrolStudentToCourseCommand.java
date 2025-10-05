package ie.universityofgalway.cs.ct5106.studentreg.application.student.dto;

import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;

public record EnrolStudentToCourseRequest(String studentId, String courseId) {
    public String studentId() {
        return studentId;
    }
    public String courseId() {
        return courseId;
    }
}
