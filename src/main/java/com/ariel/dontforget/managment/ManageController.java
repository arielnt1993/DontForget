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
        if (!activities.isEmpty()){
            return new ResponseEntity<>(activities,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("activity")
    public ResponseEntity<Activity>getActivity(@RequestBody Activity activity){
        return new ResponseEntity<>(activityService.getActivity(activity),HttpStatus.OK);
    }

    @PostMapping("activity")
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }

    @DeleteMapping("activity")
    public void  deleteActivity(@RequestBody Activity activity) {
        activityService.deleteActivity(activity);
    }

    @PutMapping("activity")
    public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity){
        return activityService.updateActivity(activity);
    }

    @PutMapping("activity/complete")
    public ResponseEntity<Activity> activityComplete(@RequestBody Activity activity){
        return activityService.activityComplete(activity);
    }
    @GetMapping("folders")
    public ResponseEntity<List<Folder>> getFolders(){
        List<Folder> folders = folderService.getFolders();
        return new ResponseEntity<>(folders,HttpStatus.OK);
    }
    @GetMapping("folder")
    public ResponseEntity<Folder> getFolder(@RequestBody Folder folder){
        return folderService.getFolder(folder);
    }
    @PostMapping("folder")
    public void registerNewFolder(@RequestBody Folder folder){
        folderService.addFolder(folder);
    }
    @PutMapping("folder")
    public ResponseEntity<Folder> updateFolder(@RequestBody Folder folder){
        return folderService.updateFolder(folder);
    }
}
