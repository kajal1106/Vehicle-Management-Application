package com.tus.vehicle_mgmt.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
    	List<VehicleDTO> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles;
    }

    @Override
    public Optional<VehicleDTO> getVehicleById(Long id) {
    	return vehicleRepository.findById(id);
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
    	return vehicleRepository.save(vehicleDTO);
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Optional<VehicleDTO> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            VehicleDTO existingVehicle = optionalVehicle.get();
            // Use a DTO mapping tool for cleaner code
            existingVehicle.setMake(vehicleDTO.getMake());
            existingVehicle.setModel(vehicleDTO.getModel());
            existingVehicle.setYear(vehicleDTO.getYear());

            return vehicleRepository.save(existingVehicle);
        } else {
            // Handle not found scenario
            throw new NoSuchElementException("Vehicle with ID " + id + " not found");
        }
    }


    @Override
    public boolean deleteVehicle(Long id) {
    	  if (vehicleRepository.existsById(id)) {
    	         vehicleRepository.deleteById(id);
    	         return true;
    	     }
    	     return false;
    }
    
    @Override
    public List<Object[]> getVehicleDetailsWithOwnerFirstName() {
        return vehicleRepository.findAllWithOwner();
    }
}
