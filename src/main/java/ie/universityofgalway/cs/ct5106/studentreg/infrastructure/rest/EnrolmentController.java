package ie.universityofgalway.cs.ct5106.studentreg.infrastructure.rest;

import ie.universityofgalway.cs.ct5106.studentreg.application.enrolment.*;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.*;
import ie.universityofgalway.cs.ct5106.studentreg.infrastructure.rest.dto.EnrolStudentToCourseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students/{studentId}/enrolments")
public class EnrolmentController {
    private final EnrolStudentToCourseUseCase enrolStudentUseCase;
    private final WithdrawStudentFromCourseUseCase withdrawUseCase;
    private final CompleteCourseUseCase completeUseCase;
    private final RemoveStudentFromCourseUseCase removeUseCase;
    private final ViewStudentCoursesUseCase viewCoursesUseCase;

    public EnrolmentController(EnrolStudentToCourseUseCase enrolStudentUseCase,
                               WithdrawStudentFromCourseUseCase withdrawUseCase,
                               CompleteCourseUseCase completeUseCase,
                               RemoveStudentFromCourseUseCase removeUseCase,
                               ViewStudentCoursesUseCase viewCoursesUseCase) {
        this.enrolStudentUseCase = enrolStudentUseCase;
        this.withdrawUseCase = withdrawUseCase;
        this.completeUseCase = completeUseCase;
        this.removeUseCase = removeUseCase;
        this.viewCoursesUseCase = viewCoursesUseCase;
    }

    @PostMapping
    public ResponseEntity<EnrolStudentToCourseResponse> enrol(@PathVariable String studentId,
                                                              @RequestBody EnrolStudentToCourseRequest request) {
        System.out.println(request);
        EnrolStudentToCourseCommand command = new EnrolStudentToCourseCommand(studentId, request.courseId());
        return ResponseEntity.ok(enrolStudentUseCase.execute(command));
    }

    @PutMapping("/{courseId}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable String studentId, @PathVariable String courseId) {
        withdrawUseCase.execute(new WithdrawStudentFromCourseCommand(studentId, courseId));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}/complete")
    public ResponseEntity<Void> complete(@PathVariable String studentId, @PathVariable String courseId) {
        completeUseCase.execute(new CompleteCourseCommand(studentId, courseId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> remove(@PathVariable String studentId, @PathVariable String courseId) {
        removeUseCase.execute(new RemoveStudentFromCourseCommand(studentId, courseId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentCoursesResponse.StudentCourseDTO>> getCourses(@PathVariable String studentId) {
        return ResponseEntity.ok(viewCoursesUseCase.execute(new StudentCoursesRequest(studentId)).courses());
    }
}
