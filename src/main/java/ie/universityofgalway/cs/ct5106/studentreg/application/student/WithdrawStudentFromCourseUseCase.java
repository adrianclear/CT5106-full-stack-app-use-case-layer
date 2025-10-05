package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.common.exceptions.StudentNotFoundException;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.WithdrawStudentFromCourseCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.WithdrawStudentFromCourseResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.UUID;


public class WithdrawStudentFromCourseUseCase implements UseCase<WithdrawStudentFromCourseCommand, WithdrawStudentFromCourseResponse> {
    private final StudentRepository studentRepository;

    public WithdrawStudentFromCourseUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public WithdrawStudentFromCourseResponse execute(WithdrawStudentFromCourseCommand command) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(command.studentId())))
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        student.withdrawFromCourse(CourseId.of(UUID.fromString(command.courseId())));

        studentRepository.save(student);
        return new WithdrawStudentFromCourseResponse(command.studentId(), command.courseId());
    }
}
