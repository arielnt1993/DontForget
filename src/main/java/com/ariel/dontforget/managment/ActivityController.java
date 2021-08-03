package com.ariel.dontforget.managment;
import com.ariel.dontforget.activities.Activity;
import com.ariel.dontforget.activities.ActivityService;
import com.ariel.dontforget.folders.Folder;
import com.ariel.dontforget.folders.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping(value = "api/manager/")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService, FolderService folderService){
        this.activityService = activityService;
    }

    @GetMapping(value = "activities")
    public ResponseEntity<List<Activity>> getActivities(){
        return activityService.getActivities();
    }

    @GetMapping("activity")
    public ResponseEntity<Activity>getActivity(@RequestBody Activity activity){
        return activityService.getActivity(activity);
    }

    @PostMapping("activity")
    public ResponseEntity<Activity> registerNewActivity(@RequestBody Activity activity){
        return activityService.addActivity(activity);
    }

    @DeleteMapping("activity")
    public ResponseEntity<Activity>  deleteActivity(@RequestBody Activity activity) {
        return activityService.deleteActivity(activity);
    }

    @PutMapping("activity")
    public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity){
        return activityService.updateActivity(activity);
    }

    @PutMapping("activity/complete")
    public ResponseEntity<Activity> activityComplete(@RequestBody Activity activity){
        return activityService.activityComplete(activity);
    }

}
