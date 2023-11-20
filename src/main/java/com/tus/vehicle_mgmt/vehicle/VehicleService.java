package com.tus.vehicle_mgmt.vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<VehicleDTO> getAllVehicles();
    Optional<VehicleDTO> getVehicleById(Long id);
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);
    VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);
    boolean deleteVehicle(Long id);
    List<Object[]> getVehicleDetailsWithOwnerFirstName();
}