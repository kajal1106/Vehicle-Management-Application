package com.tus.vehicle_mgmt.vehicle;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing vehicles.
 */
public interface VehicleService {

    /**
     * Get a list of all vehicles.
     *
     * @return List of VehicleDTO objects.
     */
    List<VehicleDTO> getAllVehicles();

    /**
     * Get a vehicle by its ID.
     *
     * @param id The ID of the vehicle.
     * @return Optional containing the VehicleDTO if found, empty otherwise.
     */
    Optional<VehicleDTO> getVehicleById(Long vehicleid);

    /**
     * Create a new vehicle.
     *
     * @param vehicleDTO The VehicleDTO object representing the new vehicle.
     * @return The created VehicleDTO.
     */
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    /**
     * Update an existing vehicle.
     *
     * @param id         The ID of the vehicle to update.
     * @param vehicleDTO The updated VehicleDTO object.
     * @return The updated VehicleDTO.
     */
    VehicleDTO updateVehicle(Long vehicleid, VehicleDTO vehicleDTO);

    /**
     * Delete a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return True if the vehicle was deleted, false if it was not found.
     */
    boolean deleteVehicle(Long vehicleid);

    /**
     * Get details of all vehicles along with owner first names.
     *
     * @return List of Object arrays containing vehicle details with owner first names.
     */
    List<Object[]> getVehicleDetailsWithOwnerFirstName();
    /**
     * Get the price of a car by make.
     *
     * @param make The make of the car.
     * @return The price of the car.
     */
    Double getPriceByMake(String make);

	List<VehicleDTO> getVehiclesByEngineType(String engine);
}
