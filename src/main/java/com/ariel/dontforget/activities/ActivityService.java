package com.ariel.dontforget.activities;

import com.ariel.dontforget.folders.Folder;
import com.ariel.dontforget.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            folder.ifPresent(value -> activity.setFolderName(value.getName()));
        });
        return activities;
    }
    public Activity getActivity(String name){
        if(activityRepository.findByName(name).isPresent()){
            return activityRepository.findByName(name).get();
        }else {
            throw new IllegalStateException("activity does not exists");
        }

    }
}
