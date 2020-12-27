package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.model.Comment;
import com.lamnguyen.GACAcademicsserver.model.Question;
import com.lamnguyen.GACAcademicsserver.model.Student;
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

    @DeleteMapping(path = "/{id}")
    public void deleteQuestion(@PathVariable("id") String id) {
        questionService.deleteQuestion(id);
    }

    @PutMapping(path = "/{id}")
    public void addComment(@PathVariable("id") String id, @Valid @NotNull @RequestBody Comment comment) {
        logger.info("Adding comment");
        questionService.addComment(id, comment);
    }

    @PutMapping(path = "/edit-comment/{id}")
    public void editComment(@PathVariable("id") String id, @Valid @NotNull @RequestBody Comment comment) {
        logger.info("Editing comment");
        questionService.editComment(id, comment);
    }
    @PutMapping(path = "/delete-comment/{id}")
    public void deleteComment(@PathVariable("id") String qid, @Valid @NotNull @RequestBody String commentId) {
        logger.info("Deleting comment");
        questionService.deleteComment(qid, commentId);
    }
    @PutMapping(path = "/like/{id}")
    public void likeQuestion(@PathVariable("id") String id, @Valid @NotNull @RequestBody Student student) {
        logger.info("Liking question");
        questionService.likeQuestion(id, student);
    }

    @PutMapping(path = "/unlike/{id}")
    public void unLikeQuestion(@PathVariable("id") String id, @RequestBody Student student) {
        logger.info("Unliking question");
        questionService.unlikeQuestion(id, student);
    }

}
