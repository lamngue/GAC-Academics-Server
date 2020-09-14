package com.lamnguyen.GACAcademicsserver.dao;

import com.lamnguyen.GACAcademicsserver.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorDAO extends MongoRepository<Professor, String> {

}
