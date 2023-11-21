package com.tus.vehicle_mgmt.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public MaintenanceDTO getMaintenanceTaskById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceTaskById(id);
    }

    @PostMapping
    public MaintenanceDTO createMaintenanceTask(@RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceService.createMaintenanceTask(maintenanceDTO);
    }

    @PutMapping("/{id}")
    public MaintenanceDTO updateMaintenanceTask(@PathVariable Long id, @RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceService.updateMaintenanceTask(id, maintenanceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenanceTask(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceTask(id);
    }
}
