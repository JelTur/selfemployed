package com.jel.selfemployed.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String taskTitle;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    private String taskStatus;
    @Temporal(TemporalType.DATE)
    private Date taskStartDate;
    private String activityType;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TaskTime> taskTimes;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Set<TaskTime> getTaskTimes() {
        return taskTimes;
    }

    public void setTaskTimes(Set<TaskTime> taskTimes) {
        this.taskTimes = taskTimes;
    }

    public int getTotalHours() {
        int totalHoursByTask = 0;
        for (TaskTime taskTime: taskTimes) {
            totalHoursByTask = totalHoursByTask + taskTime.getSessionHours();
        }

        return totalHoursByTask;
    }
}