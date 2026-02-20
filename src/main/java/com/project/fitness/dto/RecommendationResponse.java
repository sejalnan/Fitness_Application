package com.project.fitness.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RecommendationResponse {

    private String Id;

    private String type;

    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    private String userId;

    private String activityId;
}
