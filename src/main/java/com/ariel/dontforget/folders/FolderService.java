package com.ariel.dontforget.folders;
import com.ariel.dontforget.activities.Activity;
import com.ariel.dontforget.activities.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<Folder> getFolders(){
        List<Folder> folders = folderRepository.findAll();
        List<Activity> activities = activityRepository.findAll();

        folders.forEach(folder -> {
            folder.setActivities(activities.stream()
                    .filter(activity -> activity.getFolder_id().equals(folder.getId()))
                    .collect(Collectors.toList()));
        });
        return folders;
    }

    public void addFolder(Folder folder){
        Optional<Folder> folderOptional = folderRepository.findFolderByName(folder.getName());
        if(folderOptional.isPresent()){
            throw new IllegalStateException("folder already exists");
        }
        folderRepository.save(folder);
    }
}
