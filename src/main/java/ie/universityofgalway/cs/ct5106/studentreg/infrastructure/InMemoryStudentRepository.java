package ie.universityofgalway.cs.ct5106.studentreg.infrastructure;

import ie.universityofgalway.cs.ct5106.studentreg.domain.student.EmailAddress;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.Student;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStudentRepository implements StudentRepository {

    private final Map<UUID, Student> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Student> findById(StudentId id) {
        return Optional.ofNullable(store.get(id.asUuid()));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Student> findByEmail(EmailAddress email) {
        return store.values().stream().filter(s -> s.email().equals(email)).findFirst();
    }

    @Override
    public void save(Student student) {
        store.put(student.id().asUuid(), student);
    }

    @Override
    public void deleteById(StudentId id) {
        store.remove(id.asUuid());
    }
}
