package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.model.Comment;
import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.model.Question;
import com.lamnguyen.GACAcademicsserver.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionController {
    private final QuestionService questionService;
    Logger logger = LoggerFactory.getLogger(QuestionController.class);

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        logger.info("Adding question");
        return questionService.addQuestion(question);
    }

    @GetMapping
    public List<Question> getQuestions() {
        logger.info("getting questions");
        return questionService.getAllQuestions();
    }

    @GetMapping(path = "/{id}")
    public Question getQuestionById(@PathVariable("id") String id) {
        return questionService.getQuestion(id).orElse(null);
    }

    @PutMapping(path = "/{id}")
    public void addComment(@PathVariable("id") String id, @Valid @NotNull @RequestBody Comment comment) {
        questionService.addComment(id, comment);
    }

    @PutMapping(path = "/delete-comment/{id}")
    public void deleteComment(@PathVariable("id") String qid, @Valid @NotNull @RequestBody String commentId) {
        questionService.deleteComment(qid, commentId);
    }

}
