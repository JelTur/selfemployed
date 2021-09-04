package com.jel.selfemployed.Model;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String taskTitle;
    private String taskProject;
    private String taskClient;
    private String taskStatus;
    private String taskStartDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskProject() {
        return taskProject;
    }

    public void setTaskProject(String taskProject) {
        this.taskProject = taskProject;
    }

    public String getTaskClient() {
        return taskClient;
    }

    public void setTaskClient(String taskClient) {
        this.taskClient = taskClient;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }
}

