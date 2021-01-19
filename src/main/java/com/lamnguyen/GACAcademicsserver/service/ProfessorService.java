package com.lamnguyen.GACAcademicsserver.service;

import com.lamnguyen.GACAcademicsserver.dao.ProfessorDAO;
import com.lamnguyen.GACAcademicsserver.model.Professor;
import com.lamnguyen.GACAcademicsserver.model.Rating;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            rating.setId(new ObjectId().toString());
            ratings.add(rating);
            foundProf.setRatings(ratings);
            this.professorDAO.save(foundProf);
        }
    }

    public void updateRatings(String id, String ratingId, String studentId, Boolean isLike) {
        Professor foundProf = this.professorDAO.findById(id).orElse(null);
        if (foundProf != null) {
            ArrayList<Rating> ratings = foundProf.getRatings();
            Rating rating = ratings.stream().filter(rating1 -> rating1.getId().equals(ratingId)).collect(Collectors.toList()).get(0);
            Integer likeCount = rating.getLikes();
            Integer dislikeCount = rating.getDislikes();
            if (isLike) {
                rating.getLikedBy().add(studentId);
                if (rating.getDislikedBy().contains(studentId)) {
                    rating.getDislikedBy().remove(studentId);
                }
                likeCount++;
                if (dislikeCount > 0) {
                    dislikeCount--;
                }
            } else {
                rating.getDislikedBy().add(studentId);
                if (rating.getLikedBy().contains(studentId)) {
                    rating.getLikedBy().remove(studentId);
                }
                dislikeCount++;
                if (likeCount > 0) {
                    likeCount--;
                }
            }
            rating.setLikes(likeCount);
            rating.setDislikes(dislikeCount);
            foundProf.setRatings(ratings);
            this.professorDAO.save(foundProf);
        }
    }
}

