package ie.universityofgalway.cs.ct5106.studentreg.application.student;

import ie.universityofgalway.cs.ct5106.studentreg.application.common.UseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.ViewAllStudentsQuery;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.ViewAllStudentsResponse;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.List;

public class ViewAllStudentsUseCase implements UseCase<ViewAllStudentsQuery, ViewAllStudentsResponse> {

    private final StudentRepository studentRepository;

    public ViewAllStudentsUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ViewAllStudentsResponse execute(ViewAllStudentsQuery query) {
        var students = studentRepository.findAll().stream()
                .map(s -> new ViewAllStudentsResponse.StudentSummary(
                        s.id().toString(),
                        s.name().fullName(),
                        s.email().value()))
                .toList();
        return new ViewAllStudentsResponse(students);
    }


}
