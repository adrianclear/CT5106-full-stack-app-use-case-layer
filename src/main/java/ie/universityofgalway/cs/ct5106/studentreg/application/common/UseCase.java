package ie.universityofgalway.cs.ct5106.studentreg.application.common;

public interface UseCase<I, O> {
    O execute(I input);
}
