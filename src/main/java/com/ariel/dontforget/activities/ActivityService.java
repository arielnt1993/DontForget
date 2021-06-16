package com.ariel.dontforget.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public void addActivity(Activity activity){
        Optional<Activity> acttivityOld = activityRepository.findByName(activity.getName());
        if(acttivityOld.isPresent()){
            throw new IllegalStateException("la actividad ya existe");
        }
        activityRepository.save(activity);
    }
    public List<Activity> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        return activities;
    }
}
