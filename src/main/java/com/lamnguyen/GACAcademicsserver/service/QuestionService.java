package com.lamnguyen.GACAcademicsserver.service;

import com.lamnguyen.GACAcademicsserver.dao.QuestionDAO;
import com.lamnguyen.GACAcademicsserver.model.Comment;
import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.model.Question;
import com.lamnguyen.GACAcademicsserver.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public Question addQuestion(Question question) {
        Question questionExist = this.questionDAO.findById(question.getId()).orElse(null);
        if (questionExist == null) {
            question.setLikedBy(new ArrayList<>());
            this.questionDAO.insert(question);
        }
        return question;
    }

    public List<Question> getAllQuestions() {
        return this.questionDAO.findAll();
    }

    public Optional<Question> getQuestion(String id) {
        return this.questionDAO.findById(id);
    }

    public void likeQuestion(String questionId, Student student) {
        Question foundQuestion = this.questionDAO.findById(questionId).orElse(null);
        if (foundQuestion != null) {
            ArrayList<Student> likedBy = foundQuestion.getLikedBy();
            likedBy.add(student);
            foundQuestion.setLikedBy(likedBy);
            this.questionDAO.save(foundQuestion);
        }
    }

    public void unlikeQuestion(String questionId, Student student) {
        Question foundQuestion = this.questionDAO.findById(questionId).orElse(null);
        if (foundQuestion != null) {
            ArrayList<Student> likedBy = foundQuestion.getLikedBy();
            likedBy.removeIf(student1 -> student1.getId().equals(student.getId()));
            foundQuestion.setLikedBy(likedBy);
            this.questionDAO.save(foundQuestion);
        }
    }

    public void deleteQuestion(String questionId) {
        this.questionDAO.deleteById(questionId);
    }

    public void addComment(String questionId, Comment comment) {
        Question foundQuestion = this.questionDAO.findById(questionId).orElse(null);
        if (foundQuestion != null) {
            ArrayList<Comment> comments = foundQuestion.getComments();
            comments.add(comment);
            foundQuestion.setComments(comments);
            this.questionDAO.save(foundQuestion);
        }
    }

    public void editComment(String questionId, Comment modifiedComment) {
        Question foundQuestion = this.questionDAO.findById(questionId).orElse(null);
        if (foundQuestion != null) {
            ArrayList<Comment> comments = foundQuestion.getComments();
            for (Comment comment : comments) {
                if (comment.getId().equals(modifiedComment.getId())) {
                    comment.setContent(modifiedComment.getContent());
                }
            }
            foundQuestion.setComments(comments);
            this.questionDAO.save(foundQuestion);
        }
    }

    public void deleteComment(String questionId, String commentId) {
        Question foundQuestion = this.questionDAO.findById(questionId).orElse(null);
        if (foundQuestion != null) {
            ArrayList<Comment> comments = foundQuestion.getComments();
            comments.removeIf(comment -> comment.getId().equals(commentId));
            foundQuestion.setComments(comments);
            this.questionDAO.save(foundQuestion);
        }
    }

}
