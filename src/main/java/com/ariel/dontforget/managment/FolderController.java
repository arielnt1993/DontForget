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
public class FolderController {
    private final FolderService folderService;

    @Autowired
    public FolderController(ActivityService activityService,FolderService folderService){
        this.folderService = folderService;
    }

    @GetMapping("folders")
    public ResponseEntity<List<Folder>> getFolders(){
        return folderService.getFolders();
    }
    @GetMapping("folder")
    public ResponseEntity<Folder> getFolder(@RequestBody Folder folder){
        return folderService.getFolder(folder);
    }
    @PostMapping("folder")
    public ResponseEntity<Folder> registerNewFolder(@RequestBody Folder folder){
        return folderService.addFolder(folder);
    }
    @PutMapping("folder")
    public ResponseEntity<Folder> updateFolder(@RequestBody Folder folder){
        return folderService.updateFolder(folder);
    }
}
