package ie.universityofgalway.cs.ct5106.studentreg.infrastructure.rest;

import ie.universityofgalway.cs.ct5106.studentreg.application.student.AddStudentUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.DeleteStudentUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.UpdateStudentDetailsUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.ViewAllStudentsUseCase;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.AddStudentResponse;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.ViewAllStudentsResponse;
import ie.universityofgalway.cs.ct5106.studentreg.infrastructure.rest.dto.AddStudentRequest;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.AddStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.infrastructure.rest.dto.UpdateStudentRequest;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.UpdateStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.UpdateStudentResponse;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.DeleteStudentCommand;
import ie.universityofgalway.cs.ct5106.studentreg.application.student.dto.ViewAllStudentsQuery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final AddStudentUseCase addStudentUseCase;
    private final ViewAllStudentsUseCase viewAllStudentsUseCase;
    private final UpdateStudentDetailsUseCase updateStudentDetailsUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;

    public StudentController(AddStudentUseCase addStudentUseCase,
                             ViewAllStudentsUseCase viewAllStudentsUseCase,
                             UpdateStudentDetailsUseCase updateStudentDetailsUseCase,
                             DeleteStudentUseCase deleteStudentUseCase) {
        this.addStudentUseCase = addStudentUseCase;
        this.viewAllStudentsUseCase = viewAllStudentsUseCase;
        this.updateStudentDetailsUseCase = updateStudentDetailsUseCase;
        this.deleteStudentUseCase = deleteStudentUseCase;
    }

    //Reuse application layer DTOs for simplicity
    @PostMapping
    public ResponseEntity<AddStudentResponse> addStudent(@RequestBody AddStudentRequest request) {
        AddStudentCommand command = new AddStudentCommand(request.firstName(), request.lastName(), request.email());
        return ResponseEntity.ok(addStudentUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<ViewAllStudentsResponse> getAllStudents() {
        return ResponseEntity.ok(viewAllStudentsUseCase.execute(new ViewAllStudentsQuery()));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<UpdateStudentResponse> updateStudent(@PathVariable String studentId,
                                                               @RequestBody UpdateStudentRequest request) {
        UpdateStudentCommand command = new UpdateStudentCommand(studentId, request.firstName(),request.lastName(), request.email());
        return ResponseEntity.ok(updateStudentDetailsUseCase.execute(command));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        deleteStudentUseCase.execute(new DeleteStudentCommand(studentId));
        return ResponseEntity.noContent().build();
    }


}
