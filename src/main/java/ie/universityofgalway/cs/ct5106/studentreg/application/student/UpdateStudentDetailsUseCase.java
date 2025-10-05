package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.common.exceptions.StudentNotFoundException;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.UpdateStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.UpdateStudentResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.*;

import java.util.UUID;

public class UpdateStudentDetailsUseCase implements UseCase<UpdateStudentCommand, UpdateStudentResponse> {
    private final StudentRepository studentRepository;

    public UpdateStudentDetailsUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public UpdateStudentResponse execute(UpdateStudentCommand command) {
        Student student = studentRepository.findById(StudentId.of(UUID.fromString(command.studentId())))
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        student.changeName(StudentName.of(command.newFirstName(), command.newLastName()));
        student.changeEmail(EmailAddress.of(command.newEmail()));

        studentRepository.save(student);

        return new UpdateStudentResponse(student.id().toString(), student.name().fullName(), student.email().value());
    }
}
