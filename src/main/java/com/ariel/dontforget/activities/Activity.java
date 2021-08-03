package com.ariel.dontforget.activities;
import com.ariel.dontforget.folders.Folder;
import org.hibernate.annotations.GeneratorType;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="task")
public class Activity implements Serializable{
    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence")
    @Column(nullable = false,updatable = true,unique = true)
    private Long id;
    private String name;
    private Long folderId;
    private boolean done;
    @Transient
    private Folder folder;
    public Activity(){

    }
    public Activity(String name){
        this.name = name;
    }
    public Activity(Long id,boolean done){
        this.id = id;
        this.done = done;
    }
    public Activity(Long id, String name, boolean done){

        this.id = id;
        this.name = name;
        this.done = done;
    }
    public Activity(Long id, String name,Long folderId, boolean done){

        this.id = id;
        this.name = name;
        this.folderId = folderId;
        this.done = done;
    }

    public Activity(Long id, String name,Long folderId, boolean done, Folder folder){
        this.id = id;
        this.name = name;
        this.folderId = folderId;

        this.done = done;
        this.folder = folder;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
