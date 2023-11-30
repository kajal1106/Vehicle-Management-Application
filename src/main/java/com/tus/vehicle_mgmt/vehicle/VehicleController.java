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
    @GetMapping("/{vehicleid}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long vehicleid) {
        return vehicleService.getVehicleById(vehicleid)
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
    
    
 // Endpoint for finding vehicles by engine type
    @GetMapping("/engineType/{engine}")
    public ResponseEntity<ResponseWrapper<List<VehicleDTO>>> getVehiclesByEngineType(@PathVariable String engine) {
        try {
            // Attempt to find vehicles by engine type
            List<VehicleDTO> vehicles = vehicleService.getVehiclesByEngineType(engine);
            
            // Check if the list is not empty
                return (vehicles != null) ?
                ResponseEntity.ok(new ResponseWrapper<>("List of vehicles by engine type", vehicles)) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Handle exceptions when finding vehicles by engine type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error updating vehicle", null));
        }
    }
    
    // Endpoint for retrieving details of all vehicles with owner first names
    @GetMapping("/details")
    public ResponseEntity<List<Object[]>> getVehicleDetailsWithOwnerFirstName() {
        List<Object[]> result = vehicleService.getVehicleDetailsWithOwnerFirstName();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // Endpoint for updating an existing vehicle by ID
    @PutMapping("/{vehicleid}")
    public ResponseEntity<ResponseWrapper<VehicleDTO>> updateVehicle(@PathVariable Long vehicleid, @RequestBody VehicleDTO updatedVehicle) {
        try {
            // Attempt to update an existing vehicle
            VehicleDTO updated = vehicleService.updateVehicle(vehicleid, updatedVehicle);
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
    @DeleteMapping("/{vehicleid}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long vehicleid) {
        try {
            // Attempt to delete a vehicle
            boolean deleted = vehicleService.deleteVehicle(vehicleid);
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
