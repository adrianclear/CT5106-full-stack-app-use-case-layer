package ie.universityofgalway.cs.ct5106.studentreg;

import ie.universityofgalway.cs.ct5106.studentreg.application.AdminService;
import ie.universityofgalway.cs.ct5106.studentreg.domain.course.CourseId;
import ie.universityofgalway.cs.ct5106.studentreg.domain.student.StudentId;
import ie.universityofgalway.cs.ct5106.studentreg.infrastructure.InMemoryStudentRepository;

//@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        AdminService admin = new AdminService(repo);

        StudentId aliceId = admin.addStudent("Alice", "Smith", "alice@example.com");
        CourseId compSci = CourseId.random();

        admin.registerStudentToCourse(aliceId, compSci);
        System.out.println("Alice courses: " + admin.listStudentCourses(aliceId));

        //SpringApplication.run(Main.class, args);
    }
}