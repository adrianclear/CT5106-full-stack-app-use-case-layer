package ie.universityofgalway.cs.ct5106.studentreg.application;

import ie.universityofgalway.cs.ct5106.studentreg.domain.student.*;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;

import java.util.List;
import java.util.stream.Collectors;

public class AdminService {
    private final StudentRepository students;

    public AdminService(StudentRepository students) {
        this.students = students;
    }

    public StudentId addStudent(String firstName, String lastName, String email) {
        Student s = Student.create(StudentName.of(firstName, lastName), EmailAddress.of(email));
        students.save(s);
        return s.id();
    }

    public List<Student> listStudents() {
        return students.findAll();
    }

    public void updateStudentName(StudentId id, String newFirst, String newLast) {
        Student s = students.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        s.changeName(StudentName.of(newFirst, newLast));
        students.save(s);
    }

    public void deleteStudent(StudentId id) {
        students.deleteById(id);
    }

    public void registerStudentToCourse(StudentId studentId, CourseId courseId) {
        Student s = students.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        s.enrollTo(courseId);
        students.save(s);
    }

    public void removeStudentFromCourse(StudentId studentId, CourseId courseId) {
        Student s = students.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        s.removeEnrollment(courseId);
        students.save(s);
    }

    public List<CourseId> listStudentCourses(StudentId studentId) {
        Student s = students.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        return s.enrollments().stream().map(Enrolment::courseId).collect(Collectors.toList());
    }
}
