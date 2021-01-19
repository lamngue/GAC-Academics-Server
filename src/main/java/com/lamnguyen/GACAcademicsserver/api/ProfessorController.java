package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.model.Rating;
import com.lamnguyen.GACAcademicsserver.service.ProfessorService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
class EditRating {
    private String ratingId;
    private String studentId;
    private Boolean isLike;
}

@RestController
@RequestMapping("api/professor")
public class ProfessorController {
    private final ProfessorService professorService;
    Logger logger = LoggerFactory.getLogger(ProfessorController.class);
    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public String addProfessor(@RequestBody Professor professor) {
        logger.info("Adding professor");
        boolean addedProf = professorService.addProfessor(professor);
        return addedProf ? "saved professor with: " + professor.getId() : "professor " + professor.getFullName() + " already exist.";
    }

    @GetMapping
    public List<Professor> getProfessors() {
        logger.info("getting professor");
        return professorService.getProfessors();
    }

    @GetMapping(path = "/{id}")
    public Professor getProfById(@PathVariable("id") String id) {
        return professorService.getProfessorById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProfById(@PathVariable("id") String id) {
        professorService.deleteProfessor(id);
    }

    @PutMapping(path = "/{id}")
    public void updateProfessor(@PathVariable("id") String id, @Valid @NotNull @RequestBody Rating rating) {
        logger.info("updating professor");
        professorService.updateProfessor(id, rating);
    }
    @PutMapping(path = "rating/{id}")
    public void updateRating(@PathVariable("id") String id, @RequestBody @Valid @NotNull EditRating body) {
        Boolean isLike = body.getIsLike();
        String ratingId = body.getRatingId();
        String studentId = body.getStudentId();
        professorService.updateRatings(id, ratingId, studentId, isLike);
    }
}

