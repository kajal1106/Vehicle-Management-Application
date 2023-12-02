package com.tus.vehicle_mgmt.maintenance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceDTO, Long> {

	@Query("SELECT m FROM Maintenance m WHERE m.vehicle.vehicleid = :vehicleid")
    List<MaintenanceDTO> findMaintenanceByVehicle(@Param("vehicleid") Long vehicleid);
	

    // Find maintenance records by owner ID
    @Query("SELECT DISTINCT m FROM Maintenance m " +
           "JOIN FETCH m.vehicle v " +
           "JOIN FETCH v.owner o " +
           "WHERE o.id = :ownerid")
    List<MaintenanceDTO> findMaintenanceByOwnerId(@Param("ownerid") Long ownerid);
    
    // Find maintenance records by vehicle make
    @Query("SELECT m FROM Maintenance m " +
           "JOIN FETCH m.vehicle v " +
           "JOIN FETCH v.owner o " +
           "WHERE v.make = :make")
    List<MaintenanceDTO> findMaintenanceByMake(@Param("make") String make);
    
    @Query("SELECT m FROM Maintenance m WHERE m.dueDate <= :dueDate")
    List<MaintenanceDTO> findDueMaintenanceTasks(@Param("dueDate") LocalDate dueDate);


    @Query("SELECT m FROM Maintenance m WHERE m.task = :task")
    List<MaintenanceDTO> findByTask(@Param("task") String task);
}
