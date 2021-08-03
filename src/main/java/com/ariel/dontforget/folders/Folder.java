package com.ariel.dontforget.folders;
import com.ariel.dontforget.activities.Activity;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "folders")
public class Folder {
    @Id
    @SequenceGenerator(
            name = "folder_sequence",
            sequenceName = "folder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "folder_sequence")
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    @Transient
    private List<Activity> activities;
    public Folder(){

    }
    public Folder(Long id){
        this.id = id;
    }
    public Folder(Long id, String name, List<Activity> activities){

        this.id = id;
        this.name = name;
        this.activities = activities;
    }
    public Folder(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
