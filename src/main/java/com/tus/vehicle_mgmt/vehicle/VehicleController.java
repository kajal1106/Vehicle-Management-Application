package com.tus.vehicle_mgmt.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.vehicle_mgmt.maintenance.MaintenanceDTO;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    // Injecting the VehicleService dependency
    private final VehicleService vehicleService;

    // Constructor for initializing the controller with the service
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    
    // Endpoint for creating a new vehicle
    @PostMapping
    public ResponseEntity<ResponseWrapper<VehicleDTO>> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
        	// Attempt to create a new vehicle
            VehicleDTO createdVehicle = vehicleService.createVehicle(vehicleDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Vehicle created successfully", createdVehicle));
        } catch (Exception e) {
            // Handle exceptions when creating a vehicle
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error creating vehicle", null));
        }
    }

    // Endpoint for retrieving all vehicles
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        // Retrieve a list of all vehicles
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    
    // Endpoint for retrieving a vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long vehicle_id) {
        return vehicleService.getVehicleById(vehicle_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
 // Get the price of a car by make
    @GetMapping("/price/{make}")
    public Map<String, Object> getMakeAndPrice(@PathVariable String make) {
        Map<String, Object> result = new HashMap<>();
        Double price = vehicleService.getPriceByMake(make);
        result.put("make", make);
        result.put("price", price);
        return result;
    }
    
    // Endpoint for retrieving details of all vehicles with owner first names
    @GetMapping("/details")
    public ResponseEntity<List<Object[]>> getVehicleDetailsWithOwnerFirstName() {
        List<Object[]> result = vehicleService.getVehicleDetailsWithOwnerFirstName();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // Endpoint for retrieving maintenance tasks by vehicle make
    @GetMapping("/maintenance/{make}")
    public ResponseEntity<List<MaintenanceDTO>> getMaintenanceByMake(@PathVariable String make) {
        List<MaintenanceDTO> maintenanceList = vehicleService.getMaintenanceByMake(make);
        return ResponseEntity.ok(maintenanceList);
    }

    // Endpoint for retrieving maintenance tasks by owner ID
    @GetMapping("/maintenance/owner/{ownerId}")
    public ResponseEntity<List<MaintenanceDTO>> getMaintenanceByOwnerId(@PathVariable Long ownerId) {
        List<MaintenanceDTO> maintenanceList = vehicleService.getMaintenanceByOwnerId(ownerId);
        return ResponseEntity.ok(maintenanceList);
    }
    
    // Endpoint for updating an existing vehicle by ID
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<VehicleDTO>> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO updatedVehicle) {
        try {
            // Attempt to update an existing vehicle
            VehicleDTO updated = vehicleService.updateVehicle(id, updatedVehicle);
            return (updated != null) ?
                    ResponseEntity.ok(new ResponseWrapper<>("Vehicle updated successfully", updated)) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Handle exceptions when updating a vehicle
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error updating vehicle", null));
        }
    }

    // Endpoint for deleting a vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        try {
            // Attempt to delete a vehicle
            boolean deleted = vehicleService.deleteVehicle(id);
            return (deleted) ?
                    ResponseEntity.ok("Vehicle deleted successfully") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Handle exceptions when deleting a vehicle
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting vehicle");
        }
    }
    
    // Inner class for wrapping API responses with a message and data
    public class ResponseWrapper<T> {
        private final String message;
        private final T data;

        // Constructor for ResponseWrapper
        public ResponseWrapper(String message, T data) {
            this.message = message;
            this.data = data;
        }
        // Getter for message & data
        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }
}
