package ie.universityofgalway.cs.ct5106.studentreg.application.enrolment;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.CompleteCourseCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.CompleteCourseResponse;

import java.util.UUID;

public class CompleteCourseUseCase implements UseCase<CompleteCourseCommand, CompleteCourseResponse> {
    private final StudentRepository studentRepository;

    public CompleteCourseUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CompleteCourseResponse execute(CompleteCourseCommand command) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(command.studentId())))
                .orElseThrow(() -> new IllegalStateException("Student not found"));

        student.completeCourse(CourseId.of(UUID.fromString(command.courseId())));

        studentRepository.save(student);
        return new CompleteCourseResponse(command.studentId(), command.courseId());
    }
}
