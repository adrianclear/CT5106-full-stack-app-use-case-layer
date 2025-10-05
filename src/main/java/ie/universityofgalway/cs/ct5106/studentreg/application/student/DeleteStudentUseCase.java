package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.DeleteStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.DeleteStudentResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.UUID;

public class DeleteStudentUseCase implements UseCase<DeleteStudentCommand, DeleteStudentResponse> {

    private final StudentRepository studentRepository;

    public DeleteStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public DeleteStudentResponse execute(DeleteStudentCommand command) {
        studentRepository.deleteById(StudentId.of(UUID.fromString(command.studentId())));
        return new DeleteStudentResponse(command.studentId());
    }
}
