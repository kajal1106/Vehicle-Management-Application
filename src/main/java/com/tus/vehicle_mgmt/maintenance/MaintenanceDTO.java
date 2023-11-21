package com.tus.vehicle_mgmt.maintenance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tus.vehicle_mgmt.vehicle.VehicleDTO;

@Entity(name = "Maintenance")
public class MaintenanceDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Long maintenance_id;
    
    
    @Column(name = "task")
    private String task;
    
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleDTO vehicle;


    // Constructors

    public MaintenanceDTO() {
    }

    public MaintenanceDTO(String task) {
        this.task = task;
    }
    
    public MaintenanceDTO(String task, VehicleDTO vehicle) {
        this.task = task;
        this.vehicle = vehicle;
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