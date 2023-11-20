package com.tus.vehicle_mgmt.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceDTO, Long> {
    // Custom queries if needed
}
