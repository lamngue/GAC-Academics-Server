package com.lamnguyen.GACAcademicsserver.service;

import com.lamnguyen.GACAcademicsserver.dao.ProfessorDAO;
import com.lamnguyen.GACAcademicsserver.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private final ProfessorDAO professorDAO;

    public ProfessorService(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }


    public void addProfessor(Professor professor) {
        Professor profExist = this.professorDAO.findById(professor.getId()).orElse(null);
        if (profExist == null) {
            this.professorDAO.insert(professor);
            return;
        }
    }

    public List<Professor> getProfessors() {
        return this.professorDAO.findAll();
    }

    public Optional<Professor> getProfessorById(String id) {
        return this.professorDAO.findById(id);
    }

    public void deleteProfessor(String id) {
        this.professorDAO.deleteById(id);
    }

    public void updateProfessor(String id, Professor professor) {
        Professor foundProf = this.professorDAO.findById(id).orElse(null);
        if (foundProf != null) {
            foundProf.setFullName(professor.getFullName());
            foundProf.setDepartment(professor.getDepartment());
            foundProf.setImageLink(professor.getImageLink());
            foundProf.setRatings(professor.getRatings());
            this.professorDAO.save(foundProf);
        }
    }
}

