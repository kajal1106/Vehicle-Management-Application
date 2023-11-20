package com.tus.vehicle_mgmt.vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tus.vehicle_mgmt.owner.OwnerDTO;

@Entity(name = "Vehicle")
public class VehicleDTO {
	@Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "registration_number")
    private String registration;

    @Column(name = "engine_type")
    private String engine;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacture_year")
    private int year;

    @Column(name = "transmission_type")
    private String carTransmission;

    @Column(name = "vehicle_price")
    private int price;

    
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id") 
    @ManyToOne
    @JoinColumn(name = "owner_id")

    private OwnerDTO owner;

    public VehicleDTO() {
    }

    public VehicleDTO(String make, String registration, String model, String engine, int year, String carTransmission, int price) {
        this.make = make;
        this.registration = registration;
        this.engine = engine;
        this.model = model;
        this.year = year;
        this.carTransmission = carTransmission;
        this.price = price;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return carTransmission;
    }

    public void setCarTransmission(String carTransmission) {
        this.carTransmission = carTransmission;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
