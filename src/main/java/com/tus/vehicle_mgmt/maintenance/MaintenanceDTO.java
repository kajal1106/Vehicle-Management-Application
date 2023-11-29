package com.tus.vehicle_mgmt.maintenance;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tus.vehicle_mgmt.owner.OwnerDTO;
import com.tus.vehicle_mgmt.vehicle.VehicleDTO;

@Entity(name = "Maintenance")
@Table(name = "Maintenance")
public class MaintenanceDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenanceid")
    private Long maintenanceid;
    
    
    @Column(name = "task")
    private String task;
    
    @Column(name = "maintenance_date", columnDefinition = "DATE")
    private LocalDate maintenanceDate; 
    
    @ManyToOne
    @JoinColumn(name = "vehicleid")
    private VehicleDTO vehicle;


    // Constructors

    public MaintenanceDTO() {
    }

    public MaintenanceDTO(String task) {
        this.task = task;
    }
    
    public MaintenanceDTO(String task, VehicleDTO vehicle, LocalDate maintenanceDate) {
        this.task = task;
        this.vehicle = vehicle;
        this.maintenanceDate = maintenanceDate;
    }

    // Getters and setters

    public Long getId() {
        return maintenanceid;
    }

    public void setId(Long maintenanceid) {
        this.maintenanceid = maintenanceid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
    
    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }
    
    // Getter and Setter for vehicle
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}