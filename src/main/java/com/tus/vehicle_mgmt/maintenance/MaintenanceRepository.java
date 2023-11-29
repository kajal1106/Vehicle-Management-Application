package com.tus.vehicle_mgmt.maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceDTO, Long> {

	@Query("SELECT m FROM Maintenance m WHERE m.vehicle.vehicleid = :vehicleid")
    List<MaintenanceDTO> findMaintenanceByVehicle(@Param("vehicleid") Long vehicleid);
}
