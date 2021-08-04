package com.ariel.dontforget.activities;

import com.ariel.dontforget.folders.Folder;
import com.ariel.dontforget.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final FolderRepository folderRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository,FolderRepository folderRepository){
        this.activityRepository = activityRepository;
        this.folderRepository = folderRepository;
    }

    public ResponseEntity<Activity> addActivity(Activity activity){
        Optional<Activity> activityOld = activityRepository.findByName(activity.getName());
        Optional<Folder> folder = folderRepository.findFolderById(activity.getFolderId());
        if(activityOld.isPresent()){
            throw new IllegalStateException("the task already exists!");
        }
        if((activity.getFolderId()!=0)&&(folder.isEmpty())){
            throw new IllegalStateException("folder does not exists");
        }
        activityRepository.save(activity);
        return new ResponseEntity<>(activity,HttpStatus.OK);
    }
    public ResponseEntity<List<Activity>> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        if(!activities.isEmpty()){
            activities.forEach(activity -> {
                Optional<Folder> folder = folderRepository.findFolderById(activity.getFolderId());
                folder.ifPresent(activity::setFolder);
            });
            return new ResponseEntity<>(activities,HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Activity> getActivity(Activity activity){
        Optional<Activity> activityData = activityRepository.findByName(activity.getName());
        return activityData.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Activity> deleteActivity(Activity activity){
        Optional<Activity> activityData = activityRepository.findById(activity.getId());
        if(activityData.isPresent()){
            activityRepository.deleteById(activity.getId());
            return new ResponseEntity<>(activity,HttpStatus.OK);
        }else{
           return new ResponseEntity<>(activity,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Activity> updateActivity(Activity activity){
        Optional<Activity> activityData = activityRepository.findById(activity.getId());
        if(activityData.isPresent()){
            Activity oldActivity = activityData.get();
            oldActivity.setName(activity.getName());
            oldActivity.setFolderId(activity.getFolderId());
            oldActivity.setDone(activity.isDone());
            return new ResponseEntity<>(activityRepository.save(oldActivity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Activity> activityComplete(Activity activity){
        Optional<Activity> activityData = activityRepository.findById(activity.getId());
        if(activityData.isPresent()){
            Activity completedActivity = activityData.get();
            completedActivity.setDone(activity.isDone());
            return new ResponseEntity<>(activityRepository.save(completedActivity),HttpStatus.OK);
        }else {
            throw new IllegalStateException("activity not found");
        }
    }
}
