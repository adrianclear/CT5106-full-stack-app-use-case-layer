package ie.universityofgalway.cs.ct5106.studentreg.domain.course;

public interface CourseRepository {
    Course findById(CourseId id);
    void save(Course course);
}
