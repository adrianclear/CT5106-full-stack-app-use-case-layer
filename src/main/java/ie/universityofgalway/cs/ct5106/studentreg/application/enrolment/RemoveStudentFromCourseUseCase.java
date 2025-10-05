package ie.universityofgalway.cs.ct5106.studentreg.application.enrolment;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.common.exceptions.StudentNotFoundException;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.RemoveStudentFromCourseCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.RemoveStudentFromCourseResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.UUID;


public class RemoveStudentFromCourseUseCase implements UseCase<RemoveStudentFromCourseCommand, RemoveStudentFromCourseResponse> {
    private final StudentRepository studentRepository;

    public RemoveStudentFromCourseUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public RemoveStudentFromCourseResponse execute(RemoveStudentFromCourseCommand command) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(command.studentId())))
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        student.removeEnrollment(CourseId.of(UUID.fromString(command.courseId())));
        studentRepository.save(student);
        return new RemoveStudentFromCourseResponse(command.studentId(), command.courseId());
    }
}
