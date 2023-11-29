package com.tus.vehicle_mgmt.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public List<MaintenanceDTO> getAllMaintenanceTasks() {
        return maintenanceService.getAllMaintenanceTasks();
    }

    @GetMapping("/{maintenanceid}")
    public MaintenanceDTO getMaintenanceTaskById(@PathVariable Long maintenanceid) {
        return maintenanceService.getMaintenanceTaskById(maintenanceid);
    }
    
    // Endpoint for retrieving maintenance tasks by vehicle ID
    @GetMapping("/vehicle/{vehicleid}")
    public ResponseEntity<List<MaintenanceDTO>> findMaintenanceByVehicle(@PathVariable Long vehicleid) {
        List<MaintenanceDTO> maintenanceList = maintenanceService.getMaintenanceByVehicle(vehicleid);
        return new ResponseEntity<>(maintenanceList, HttpStatus.OK);
    }
    @PostMapping
    public MaintenanceDTO createMaintenanceTask(@RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceService.createMaintenanceTask(maintenanceDTO);
    }

    @PutMapping("/{maintenanceid}")
    public MaintenanceDTO updateMaintenanceTask(@PathVariable Long maintenanceid, @RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceService.updateMaintenanceTask(maintenanceid, maintenanceDTO);
    }

 
    @DeleteMapping("/{maintenanceid}")
    public ResponseEntity<String> deleteMaintenanceTask(@PathVariable Long maintenanceid) {
        try {
            // Attempt to delete a task
            boolean deleted = maintenanceService.deleteMaintenanceTask(maintenanceid);
            return (deleted) ?
                    ResponseEntity.ok("Task deleted successfully") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Handle exceptions when deleting a task
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting task");
        }
    }

}
