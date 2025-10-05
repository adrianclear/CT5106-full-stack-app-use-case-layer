package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseRepository;


public class RegisterStudentToCoursesUseCase implements UseCase<RegisterStudentToCoursesRequest, Void> {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegisterStudentToCoursesUseCase(StudentRepository studentRepository,
                                           CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public void execute(RegisterStudentToCoursesRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new StudentNotFoundException(request.studentId()));

        for (Long courseId : request.courseIds()) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException(courseId));
            Enrollment enrollment = new Enrollment(student, course);
            enrollmentRepository.save(enrollment);
        }
    }
}