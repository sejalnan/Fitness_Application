package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepo;
import com.project.fitness.repository.RecommendationRepo;
import com.project.fitness.repository.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class RecommendationService {

    public final UserRepo userRepo;

    public final ActivityRepo activityRepo;
    public final RecommendationRepo recommendationRepo;


    public Recommendation generateRecommendation(RecommendationRequest request) {
        User user =userRepo.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User Not Found"+ request.getUserId()));


        Activity activity =activityRepo.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity Not Found"+ request.getActivityId()));


        Recommendation recommendation=Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendation savedRecommendation=recommendationRepo.save(recommendation);

        return savedRecommendation;
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepo.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepo.findByActivityId(activityId);
    }
}
