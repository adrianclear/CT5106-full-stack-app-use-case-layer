package ie.universityofgalway.cs.ct5106.studentreg.application.enrolment;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.StudentCoursesRequest;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.StudentCoursesResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.UUID;

public class ViewStudentCoursesUseCase implements UseCase<StudentCoursesRequest, StudentCoursesResponse> {
    private final StudentRepository studentRepository;

    public ViewStudentCoursesUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentCoursesResponse execute(StudentCoursesRequest command) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(command.studentId())))
                .orElseThrow(() -> new IllegalStateException("Student not found"));

        return new StudentCoursesResponse(student.enrollments().stream()
                .map(e -> new StudentCoursesResponse.StudentCourseDTO(e.courseId().toString(), e.status().name()))
                .toList());
    }
}
