package com.tus.vehicle_mgmt.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDTO, Long> {
	
	List<VehicleDTO> findByMake(String make);
	
	@Query(value = "SELECT v.id, v.carTransmission, v.engine, v.make, " +
            "v.model, v.price, v.registration, v.year, " +
            "v.owner.id, v.owner.firstName " +
            "FROM Vehicle v", nativeQuery = false)
    List<Object[]> findAllWithOwner();

    
    @Query(value = "SELECT VEHICLE.VEHICLE_ID, VEHICLE.CAR_TRANSMISSION, VEHICLE.ENGINE_TYPE, VEHICLE.MAKE, " +
            "VEHICLE.MODEL, VEHICLE.VEHICLE_PRICE, VEHICLE.REGISTRATION_NUMBER, VEHICLE.MANUFACTURE_YEAR, " +
            "VEHICLE.OWNER_ID, OWNER.FIRST_NAME " +
            "FROM VEHICLE " +
            "JOIN OWNER ON VEHICLE.OWNER_ID = OWNER.OWNER_ID", nativeQuery = true)
    List<Object[]> getVehicleDetailsWithOwnerFirstName();

}
