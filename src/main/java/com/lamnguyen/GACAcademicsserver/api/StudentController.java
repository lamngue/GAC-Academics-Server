package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.model.Student;
import com.lamnguyen.GACAcademicsserver.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        logger.info("Adding student");
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getStudent() {
        logger.info("getting students");
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") String id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") String id,@Valid @NotNull @RequestBody Student student) {
        logger.info("updating student");
        studentService.updateStudent(id, student);
    }
}
