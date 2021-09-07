package com.jel.selfemployed.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.DATE)
    Date sessionDate;
    private Integer sessionHours;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Integer getSessionHours() {
        return sessionHours;
    }

    public void setSessionHours(Integer sessionHours) {
        this.sessionHours = sessionHours;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}