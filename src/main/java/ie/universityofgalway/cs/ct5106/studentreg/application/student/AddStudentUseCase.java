package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.AddStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.AddStudentResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.EmailAddress;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentName;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

public class AddStudentUseCase implements UseCase<AddStudentCommand, AddStudentResponse> {

    private final StudentRepository studentRepository;

    public AddStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public AddStudentResponse execute(AddStudentCommand request) {
        Student student = Student.create(StudentName.of(request.firstName(), request.lastName()), EmailAddress.of(request.email()));
        studentRepository.save(student);
        return new AddStudentResponse(student.id().toString(), student.name().fullName(), student.email().toString());
    }
}
