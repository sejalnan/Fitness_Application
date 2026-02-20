package com.project.fitness.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {

    private String userId;

    private String activityId;

//    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;




}
