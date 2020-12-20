package com.lamnguyen.GACAcademicsserver.dao;

import com.lamnguyen.GACAcademicsserver.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends MongoRepository<Question, String> {

}
