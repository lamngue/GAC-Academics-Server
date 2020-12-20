package com.lamnguyen.GACAcademicsserver.service;

import com.lamnguyen.GACAcademicsserver.dao.StudentDAO;
import com.lamnguyen.GACAcademicsserver.model.Semester;
import com.lamnguyen.GACAcademicsserver.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student addStudent(Student student) {
        Student studentExist = this.studentDAO.findById(student.getId()).orElse(null);
        if (studentExist == null) {
            this.studentDAO.insert(student);
        }
        return student;
    }
    public List<Student> getAllStudents() {
        return this.studentDAO.findAll();
    }

    public Student getStudentById(String id) {
        return this.studentDAO.findById(id).orElse(null);
    }

    public void updateStudent(String id, Student student) {
        Student foundStudent = this.studentDAO.findById(id).orElse(null);
        if (foundStudent != null) {
            foundStudent.setEndDate(student.getEndDate());
            foundStudent.setClassesPlan(student.getClassesPlan());
            this.studentDAO.save(foundStudent);
        }
    }
}