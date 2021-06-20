package com.ariel.dontforget.activities;

import com.ariel.dontforget.folders.Folder;
import com.ariel.dontforget.folders.FolderRepository;
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
    private final FolderRepository folderRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository,FolderRepository folderRepository){
        this.activityRepository = activityRepository;
        this.folderRepository = folderRepository;
    }

    public void addActivity(Activity activity){
        Optional<Activity> activityOld = activityRepository.findByName(activity.getName());
        Optional<Folder> folder = folderRepository.findFolderById(activity.getFolder_id());
        if(activityOld.isPresent()){
            throw new IllegalStateException("la actividad ya existe");
        }
        if((activity.getFolder_id()!=0)&&(folder.isEmpty())){
            throw new IllegalStateException("folder does not exists!!");
        }
        activityRepository.save(activity);
    }
    public List<Activity> getActivities(){

        return activityRepository.findAll();
    }
}
