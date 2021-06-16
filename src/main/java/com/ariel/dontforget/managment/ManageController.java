package com.ariel.dontforget.managment;
import com.ariel.dontforget.activities.Activity;
import com.ariel.dontforget.activities.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/manager/")
public class ManageController {
    private final ActivityService activityService;

    @Autowired
    public ManageController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("activity")
    public ResponseEntity<List<Activity>> getActivities(){
        List<Activity> activities = activityService.getActivities();
        return new ResponseEntity<>(activities,HttpStatus.OK);
    }
    @PostMapping("activity")
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }
}
