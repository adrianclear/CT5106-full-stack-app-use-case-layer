package ie.universityofgalway.cs.ct5106.studentreg.infrastructure;

import ie.universityofgalway.cs.ct5106.studentreg.application.student.AddStudentUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.DeleteStudentUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.UpdateStudentDetailsUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.ViewAllStudentsUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {


    @Bean
    public StudentRepository studentRepository() {
        return new InMemoryStudentRepository();
    }

    @Bean
    public AddStudentUseCase addStudentUseCase(StudentRepository studentRepository) {
        return new AddStudentUseCase(studentRepository);
    }

    @Bean
    public ViewAllStudentsUseCase viewAllStudentsUseCase(StudentRepository studentRepository) {
        return new ViewAllStudentsUseCase(studentRepository);
    }

    @Bean
    public UpdateStudentDetailsUseCase updateStudentDetailsUseCase(StudentRepository studentRepository) {
        return new UpdateStudentDetailsUseCase(studentRepository);
    }

    @Bean
    public DeleteStudentUseCase deleteStudentUseCase(StudentRepository studentRepository) {
        return new DeleteStudentUseCase(studentRepository);
    }

}
