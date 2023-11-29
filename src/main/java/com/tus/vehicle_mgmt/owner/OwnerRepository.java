package com.tus.vehicle_mgmt.owner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDTO, Long> {
	// Custom query to find owners by first name
    List<OwnerDTO> findByFirstName(String firstName);

    // Custom query to find owners by last name
    List<OwnerDTO> findByLastName(String lastName);

    // Custom query to find owners by first and last name
    List<OwnerDTO> findByFirstNameAndLastName(String firstName, String lastName);
}
