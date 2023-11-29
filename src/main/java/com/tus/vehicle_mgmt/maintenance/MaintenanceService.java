package com.tus.vehicle_mgmt.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<MaintenanceDTO> getAllMaintenanceTasks() {
        return maintenanceRepository.findAll();
    }
    
    
    public List<MaintenanceDTO> getMaintenanceByVehicle(Long vehicleid){
        return maintenanceRepository.findMaintenanceByVehicle(vehicleid);
    }

    public MaintenanceDTO createMaintenanceTask(MaintenanceDTO maintenanceTask) {
        // Implement logic to create a new maintenance task
        return maintenanceRepository.save(maintenanceTask);
    }

    public MaintenanceDTO getMaintenanceTaskById(Long taskId) {
        // Implement logic to retrieve a maintenance task by ID
        return maintenanceRepository.findById(taskId).orElse(null);
    }

    public MaintenanceDTO updateMaintenanceTask(Long taskId, MaintenanceDTO updatedTask) {
        // Implement logic to update an existing maintenance task
        MaintenanceDTO existingTask = maintenanceRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            // Update properties of the existing task
            existingTask.setTask(updatedTask.getTask());

            // Save the updated task
            return maintenanceRepository.save(existingTask);
        } else {
            return null; // Task not found
        }
    }
    
    public boolean deleteMaintenanceTask(Long taskId) {
        // Implement logic to delete a maintenance task by ID
        if (maintenanceRepository.existsById(taskId)) {
            maintenanceRepository.deleteById(taskId);
            return true;
        }
		return false;
    }
}