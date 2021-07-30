package com.ariel.dontforget.managment;
import com.ariel.dontforget.activities.Activity;
import com.ariel.dontforget.activities.ActivityService;
import com.ariel.dontforget.folders.Folder;
import com.ariel.dontforget.folders.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "api/manager/")
public class ManageController {
    private final ActivityService activityService;
    private final FolderService folderService;

    @Autowired
    public ManageController(ActivityService activityService,FolderService folderService){
        this.activityService = activityService;
        this.folderService = folderService;
    }
    @GetMapping(value = "activities")
    public ResponseEntity<List<Activity>> getActivities(){
        List<Activity> activities = activityService.getActivities();
        return new ResponseEntity<>(activities,HttpStatus.OK);
    }
    @GetMapping("activity")
    public ResponseEntity<Activity>getActivity(@RequestBody Activity activity){

        return new ResponseEntity<>(activityService.getActivity(activity.getName()),HttpStatus.OK);
    }
    @PostMapping("activity")
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }


    @GetMapping("folders")
    public ResponseEntity<List<Folder>> getFolders(){
        List<Folder> folders = folderService.getFolders();

        return new ResponseEntity<>(folders,HttpStatus.OK);
    }
    @PostMapping("folder")
    public void registerNewFolder(@RequestBody Folder folder){
        folderService.addFolder(folder);
    }
}
