package com.ariel.dontforget.activities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="task")
public class Activity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = true,unique = true)
    private Long id;
    private String name;
    private Long folder_id = 0L;
    private boolean done;

    public Activity(){

    }
    public Activity(Long id, String name, boolean done){

        this.id = id;
        this.name = name;
        this.done = done;
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
}
