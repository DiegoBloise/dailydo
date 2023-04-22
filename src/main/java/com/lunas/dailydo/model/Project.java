package com.lunas.dailydo.model;

import java.sql.Date;
import java.time.LocalDate;

public final class Project {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Project(String name, String description, Date createdAt, Date updatedAt) {
        this.setName(name);
        this.setDescription(description);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }
    
    public Project() {
        this.setCreatedAt(Date.valueOf(LocalDate.now()));
        this.setUpdatedAt(Date.valueOf(LocalDate.now()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Projects{" + "id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
