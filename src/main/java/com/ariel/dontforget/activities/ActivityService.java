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

    public void addActivity(Activity activity){
        Optional<Activity> activityOld = activityRepository.findByName(activity.getName());
        Optional<Folder> folder = folderRepository.findFolderById(activity.getFolderId());
        if(activityOld.isPresent()){
            throw new IllegalStateException("la actividad ya existe");
        }
        if((activity.getFolderId()!=0)&&(folder.isEmpty())){
            throw new IllegalStateException("folder does not exists!!");
        }
        activityRepository.save(activity);
    }
    public List<Activity> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        activities.forEach(activity -> {
            Optional<Folder> folder = folderRepository.findFolderById(activity.getFolderId());
            folder.ifPresent(activity::setFolder);
        });
        return activities;
    }
    public Activity getActivity(Activity activity){
        Optional<Activity> task = activityRepository.findByName(activity.getName());
        if (task.isPresent()){
            return task.get();
        }else {
            throw new IllegalStateException("activity not found");
        }

    }
    public void deleteActivity(Activity activity){
        Optional<Activity> task = activityRepository.findById(activity.getId());
        if(task.isPresent()){
            activityRepository.deleteById(activity.getId());

        }else{
           throw new IllegalStateException("activity not found");
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
