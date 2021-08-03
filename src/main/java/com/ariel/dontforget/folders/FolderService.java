package com.ariel.dontforget.folders;
import com.ariel.dontforget.activities.Activity;
import com.ariel.dontforget.activities.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository,ActivityRepository activityRepository){
        this.folderRepository = folderRepository;
        this.activityRepository = activityRepository;
    }

    public ResponseEntity<List<Folder>> getFolders(){
        List<Folder> folders = folderRepository.findAll();
        List<Activity> activities = activityRepository.findAll();
        if(!folders.isEmpty()){
            folders.forEach(folder -> folder.setActivities(activities.stream()
                    .filter(activity -> activity.getFolderId().equals(folder.getId()))
                    .collect(Collectors.toList())));
            return new ResponseEntity<>(folders,HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    public ResponseEntity<Folder> getFolder(Folder folder){
        Optional<Folder> folderData = folderRepository.findFolderById(folder.getId());
        List<Activity> activities = activityRepository.findActivitiesByFolderId(folder.getId());
        if(folderData.isPresent()){
            folder.setActivities(activities);
            return new ResponseEntity<>(folder,HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Folder> addFolder(Folder folder){
        Optional<Folder> folderOptional = folderRepository.findFolderByName(folder.getName());
        if(folderOptional.isPresent()){
            return new ResponseEntity<>(folder,HttpStatus.IM_USED);
        }else {
            folderRepository.save(folder);
            return new ResponseEntity<>(folder,HttpStatus.OK);
        }

    }
    public ResponseEntity<Folder> updateFolder(Folder folder){
        Optional<Folder> folderData = folderRepository.findFolderById(folder.getId());
        if (folderData.isPresent()){
            Folder folderOld = folderData.get();
            folderOld.setName(folder.getName());

            return new ResponseEntity<>(folder, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(folder, HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<Folder> deleteFolder(Folder folder){
        Optional<Folder> folderData = folderRepository.findFolderById(folder.getId());
        if(folderData.isPresent()){
            folderRepository.deleteById(folder.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
