package com.project.fitness.contoller;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/trackActivity")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest){
        return  ResponseEntity.ok(activityService.trackActivity(activityRequest));

    }

    @GetMapping("/getActivity")
    public ResponseEntity<List<ActivityResponse>>
    getActivity(@RequestHeader(value = "X-User-ID") String userId){


        return ResponseEntity.ok(activityService.getActivityRepo(userId));
    }

}
