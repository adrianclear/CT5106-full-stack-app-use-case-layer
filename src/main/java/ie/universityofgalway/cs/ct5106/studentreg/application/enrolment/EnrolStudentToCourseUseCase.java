package ie.universityofgalway.cs.ct5106.studentreg.application.enrolment;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.common.exceptions.StudentNotFoundException;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.EnrolStudentToCourseCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.EnrolStudentToCourseResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;

import java.util.UUID;

public class EnrolStudentToCourseUseCase implements UseCase<EnrolStudentToCourseCommand, EnrolStudentToCourseResponse> {

    private final StudentRepository studentRepository;

    public EnrolStudentToCourseUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public EnrolStudentToCourseResponse execute(EnrolStudentToCourseCommand request) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(request.studentId())))
                .orElseThrow(() -> new StudentNotFoundException(request.studentId()));

        student.enrollTo(CourseId.of(UUID.fromString(request.courseId())));
        studentRepository.save(student);
        return new EnrolStudentToCourseResponse(student.id().toString(), request.courseId(), "ENROLLED");
    }
}