package com.lamnguyen.GACAcademicsserver.dao;

import com.lamnguyen.GACAcademicsserver.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentDAO extends MongoRepository<Student, String> {

}
