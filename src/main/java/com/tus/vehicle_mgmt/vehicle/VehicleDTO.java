package com.tus.vehicle_mgmt.vehicle;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tus.vehicle_mgmt.maintenance.MaintenanceDTO;
import com.tus.vehicle_mgmt.owner.OwnerDTO;

@Entity(name = "Vehicle")
@Table(name = "Vehicle")
public class VehicleDTO {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleid")
    private Long vehicleid;

    @Column(name = "make")
    private String make;

    @Column(name = "registration")
    private String registration;

    @Column(name = "engine")
    private String engine;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "car_transmission")
    private String car_transmission;

    @Column(name = "price")
    private double price;

    // Many vehicles can belong to one owner
    @ManyToOne
    @JoinColumn(name = "ownerid")
    private OwnerDTO owner;

    // One vehicle can have multiple maintenance tasks
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<MaintenanceDTO> maintenanceList;

    public VehicleDTO() {
    }

    // Parameterized constructor
    public VehicleDTO(Long vehicleid, String make, String registration, String model, String engine, int year, String car_transmission,
            int price) {
    	this.vehicleid = vehicleid;
        this.make = make;
        this.registration = registration;
        this.engine = engine;
        this.model = model;
        this.year = year;
        this.car_transmission = car_transmission;
        this.price = price;
    }

    // Getter and Setter methods

    public Long getId() {
        return vehicleid;
    }

    public void setId(Long vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCarTransmission() {
        return car_transmission;
    }

    public void setCarTransmission(String car_transmission) {
        this.car_transmission = car_transmission;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and Setter for owner
    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    // Getter and Setter for maintenanceList
    public List<MaintenanceDTO> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<MaintenanceDTO> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    // toString method to facilitate debugging and logging.

    @Override
    public String toString() {
        return "VehicleDTO{" + "id=" + vehicleid + ", make='" + make + '\'' + ", registration='" + registration + '\''
                + ", engine='" + engine + '\'' + ", model='" + model + '\'' + ", year=" + year
                + ", carTransmission='" + car_transmission + '\'' + ", price=" + price + ", owner=" + owner
                + ", maintenanceList=" + maintenanceList + '}';
    }
}