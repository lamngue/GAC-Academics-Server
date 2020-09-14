package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.service.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        professorService.addProfessor(professor);
        return "saved professor with: " + professor.getId();
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
    public void updateProf(@PathVariable("id") String id, @Valid @NotNull @RequestBody Professor professor) {
        logger.info("updating professor");
        professorService.updateProfessor(id, professor);
    }
}

