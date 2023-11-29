package com.tus.vehicle_mgmt.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tus.vehicle_mgmt.maintenance.MaintenanceDTO;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDTO, Long> {
    
    // Find vehicles by make
    List<VehicleDTO> findByMake(String make);
    
    // Find the price of a car by make using JPQL
    @Query("SELECT v.price FROM Vehicle v WHERE v.make = :make")
    Double findPriceByMake(@Param("make") String make);

    // Find all vehicles with owner details using JPQL
    @Query("SELECT v.vehicleid, v.make, v.registration, v.engine, v.model, v.year, v.car_transmission, v.price, " +
           "v.owner.ownerid, v.owner.firstName FROM Vehicle v")
    List<Object[]> findAllWithOwner();

    // Find vehicle details with owner first name using native SQL query
    @Query(value = "SELECT VEHICLE.VEHICLEID, VEHICLE.CAR_TRANSMISSION, VEHICLE.ENGINE, VEHICLE.MAKE, " +
            "VEHICLE.MODEL, VEHICLE.PRICE, VEHICLE.REGISTRATION, VEHICLE.YEAR, " +
            "VEHICLE.OWNERID, OWNER.FIRST_NAME " +
            "FROM VEHICLE " +
            "JOIN OWNER ON VEHICLE.OWNERID = OWNER.OWNERID", nativeQuery = true)
    List<Object[]> getVehicleDetailsWithOwnerFirstName();
    
    // Find maintenance records by vehicle make using JPQL
    @Query("SELECT m FROM Maintenance m " +
           "JOIN FETCH m.vehicle v " +
           "JOIN FETCH v.owner o " +
           "WHERE v.make = :make")
    List<MaintenanceDTO> findMaintenanceByMake(@Param("make") String make);

    // Find maintenance records by owner ID using JPQL
    @Query("SELECT DISTINCT m FROM Maintenance m " +
           "JOIN FETCH m.vehicle v " +
           "JOIN FETCH v.owner o " +
           "WHERE o.id = :ownerid")
    List<MaintenanceDTO> findMaintenanceByOwnerId(@Param("ownerid") Long ownerid);
}
