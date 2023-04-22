package com.lunas.dailydo.model;

import java.sql.Date;
import java.time.LocalDate;

public final class Task {
    private int id;
    private int projectId;
    private String name;
    private String description;
    private boolean completed;
    private String notes;
    private Date deadline;
    private Date createdAt;
    private Date updatedAt;

    public Task(int id, int projectId, String name, String description, boolean completed, String notes, Date deadline, Date createdAt, Date updatedAt) {
        this.setId(id);
        this.setProjectId(projectId);
        this.setName(name);
        this.setDescription(description);
        this.setCompleted(completed);
        this.setNotes(notes);
        this.setDeadline(deadline);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }
    
    public Task(){
        this.setCreatedAt(Date.valueOf(LocalDate.now()));
        this.setUpdatedAt(Date.valueOf(LocalDate.now()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Tasks{" + "id=" + id + ", projectId=" + projectId + ", name=" + name + ", description=" + description + ", completed=" + completed + ", notes=" + notes + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
