package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepo;
import com.project.fitness.repository.UserRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Data
@Service
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final UserRepo userRepo;


    public ActivityResponse trackActivity(ActivityRequest activityRequest)
    {
        User user=userRepo.findById(activityRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid User"+ activityRequest.getUserId()));

        Activity activity=Activity.builder()
                .user(user)
                        .type(activityRequest.getType())
                        .duration(activityRequest.getDuration())
                        .caloriesBurned(activityRequest.getCaloriesBurned())
                        .startTime(activityRequest.getStartTime())
                        .additionalMetrics(activityRequest.getAdditionalMetrics())
                        .build();


      Activity savedActivity =  activityRepo.save(activity);
      return mapToResponse(savedActivity);

    }

    private ActivityResponse mapToResponse(Activity savedActivity)

    {
        ActivityResponse response=new ActivityResponse();
        response.setId(savedActivity.getId());
        response.setUserId(savedActivity.getUser().getId());
        response.setType(savedActivity.getType());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setDuration(savedActivity.getDuration());
        response.setStartTime(savedActivity.getStartTime());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        return response;
    }

    public List<ActivityResponse> getActivityRepo(String userId) {

     List<Activity> activityList=activityRepo.findByUserId(userId);

     return  activityList.stream().map(this::mapToResponse)
             .collect(Collectors.toList());
    }
}
