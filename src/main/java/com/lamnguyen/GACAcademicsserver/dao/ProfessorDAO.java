package com.lamnguyen.GACAcademicsserver.dao;

import com.lamnguyen.GACAcademicsserver.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDAO extends MongoRepository<Professor, String> {
    Professor findByFullName(String fullName);
}
