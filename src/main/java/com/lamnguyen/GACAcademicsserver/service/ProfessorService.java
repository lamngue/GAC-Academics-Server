package com.lamnguyen.GACAcademicsserver.service;

import com.lamnguyen.GACAcademicsserver.dao.ProfessorDAO;
import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private final ProfessorDAO professorDAO;

    public ProfessorService(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }


    public boolean addProfessor(Professor professor) {
        Professor profExist = this.professorDAO.findByFullName(professor.getFullName());
        if (profExist == null) {
            this.professorDAO.insert(professor);
            return true;
        }
        return false;
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

    public void updateProfessor(String id, Rating rating) {
        Professor foundProf = this.professorDAO.findById(id).orElse(null);
        if (foundProf != null) {
            ArrayList<Rating> ratings = foundProf.getRatings();
            ratings.add(rating);
            foundProf.setRatings(ratings);
            this.professorDAO.save(foundProf);
        }
    }
}

