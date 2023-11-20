package com.tus.vehicle_mgmt.maintenance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Maintenance")
public class MaintenanceDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenance_id;

    private String task;

    // Constructors

    public MaintenanceDTO() {
    }

    public MaintenanceDTO(String task) {
        this.task = task;
    }

    // Getters and setters

    public Long getId() {
        return maintenance_id;
    }

    public void setId(Long id) {
        this.maintenance_id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}