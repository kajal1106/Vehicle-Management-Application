package com.tus.vehicle_mgmt.owner;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tus.vehicle_mgmt.vehicle.VehicleDTO;

@Entity(name = "Owner")
public class OwnerDTO {
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long owner_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @OneToMany(mappedBy = "owner")
    private List<VehicleDTO> vehicles;

    // Default constructor for JPA
    public OwnerDTO() {
    }

    // Constructor for creating instances
    public OwnerDTO(Long owner_id, String firstName, String lastName) {
        this.owner_id = owner_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters

    public Long getId() {
        return owner_id;
    }

    public void setId(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
