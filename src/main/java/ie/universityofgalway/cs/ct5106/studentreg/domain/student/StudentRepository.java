package ie.universityofgalway.cs.ct5106.studentreg.domain.student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(StudentId id);
    List<Student> findAll();
    Optional<Student> findByEmail(EmailAddress email);
    void save(Student student);
    void deleteById(StudentId id);
}
