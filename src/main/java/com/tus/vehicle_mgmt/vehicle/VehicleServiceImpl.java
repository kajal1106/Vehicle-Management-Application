package com.tus.vehicle_mgmt.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing vehicles.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    /**
     * Get a list of all vehicles.
     *
     * @return List of VehicleDTO objects.
     */
    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles;
    }

    /**
     * Get a vehicle by its ID.
     *
     * @param id The ID of the vehicle.
     * @return Optional containing the VehicleDTO if found, empty otherwise.
     */
    @Override
    public Optional<VehicleDTO> getVehicleById(Long vehicleid) {
        return vehicleRepository.findById(vehicleid);
    }

    /**
     * Get the price of a car by make.
     *
     * @param make The make of the car.
     * @return The price of the car.
     */
    @Override
    public Double getPriceByMake(String make) {
        return vehicleRepository.findPriceByMake(make);
    }
    
    /**
     * Get the list of vehicles by Engine Type.
     *
     * @param .
     * @return 
     */
    @Override
    
    public List<VehicleDTO> getVehiclesByEngineType(String engine) {
        return vehicleRepository.findByEngineType(engine);
    }


    /**
     * Create a new vehicle.
     *
     * @param vehicleDTO The VehicleDTO object representing the new vehicle.
     * @return The created VehicleDTO.
     */
    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        return vehicleRepository.save(vehicleDTO);
    }

    /**
     * Update an existing vehicle.
     *
     * @param id         The ID of the vehicle to update.
     * @param vehicleDTO The updated VehicleDTO object.
     * @return The updated VehicleDTO.
     * @throws NoSuchElementException If the vehicle with the specified ID is not found.
     */
    @Override
    public VehicleDTO updateVehicle(Long vehicleid, VehicleDTO vehicleDTO) {
        Optional<VehicleDTO> optionalVehicle = vehicleRepository.findById(vehicleid);
        if (optionalVehicle.isPresent()) {
            VehicleDTO existingVehicle = optionalVehicle.get();
            // Use a DTO mapping tool for cleaner code
            existingVehicle.setMake(vehicleDTO.getMake());
            existingVehicle.setModel(vehicleDTO.getModel());
            existingVehicle.setYear(vehicleDTO.getYear());

            return vehicleRepository.save(existingVehicle);
        } else {
            // Handle not found scenario
            throw new NoSuchElementException("Vehicle with ID " + vehicleid + " not found");
        }
    }

    /**
     * Delete a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return True if the vehicle was deleted, false if it was not found.
     */
    @Override
    public boolean deleteVehicle(Long vehicleid) {
        if (vehicleRepository.existsById(vehicleid)) {
            vehicleRepository.deleteById(vehicleid);
            return true;
        }
        return false;
    }

    /**
     * Get details of all vehicles along with owner first names.
     *
     * @return List of Object arrays containing vehicle details with owner first names.
     */
    @Override
    public List<Object[]> getVehicleDetailsWithOwnerFirstName() {
        return vehicleRepository.findAllWithOwner();
    }
}