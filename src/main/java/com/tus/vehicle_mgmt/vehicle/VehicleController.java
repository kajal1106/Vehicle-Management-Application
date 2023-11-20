package com.tus.vehicle_mgmt.vehicle;

import java.util.List;

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

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<VehicleDTO>> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO createdVehicle = vehicleService.createVehicle(vehicleDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("Vehicle created successfully", createdVehicle));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error creating vehicle", null));
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/details")
    public ResponseEntity<List<Object[]>> getVehicleDetailsWithOwnerFirstName() {
        List<Object[]> result = vehicleService.getVehicleDetailsWithOwnerFirstName();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<VehicleDTO>> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO updatedVehicle) {
        try {
            VehicleDTO updated = vehicleService.updateVehicle(id, updatedVehicle);
            return (updated != null) ?
                    ResponseEntity.ok(new ResponseWrapper<>("Vehicle updated successfully", updated)) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error updating vehicle", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        try {
            boolean deleted = vehicleService.deleteVehicle(id);
            return (deleted) ?
                    ResponseEntity.ok("Vehicle deleted successfully") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting vehicle");
        }
    }
    public class ResponseWrapper<T> {
        private final String message;
        private final T data;

        public ResponseWrapper(String message, T data) {
            this.message = message;
            this.data = data;
        }
        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }
}
