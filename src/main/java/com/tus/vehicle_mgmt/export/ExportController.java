package com.tus.vehicle_mgmt.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.vehicle_mgmt.maintenance.MaintenanceDTO;
import com.tus.vehicle_mgmt.maintenance.MaintenanceService;
import com.tus.vehicle_mgmt.owner.OwnerDTO;
import com.tus.vehicle_mgmt.owner.OwnerService;
import com.tus.vehicle_mgmt.vehicle.VehicleDTO;
import com.tus.vehicle_mgmt.vehicle.VehicleService;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final VehicleService vehicleService;
    private final OwnerService ownerService;
    private final MaintenanceService maintenanceService;

    public ExportController(VehicleService vehicleService, OwnerService ownerService, MaintenanceService maintenanceService) {
        this.vehicleService = vehicleService;
        this.ownerService = ownerService;
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/csv")
    public ResponseEntity<String> exportCsv() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            List<OwnerDTO> owners = ownerService.getAllOwners();
            List<MaintenanceDTO> maintenanceList = maintenanceService.getAllMaintenanceTasks();

            String csvData = CsvConverter.convertDataToCsv(vehicles, owners, maintenanceList);

            // Save the CSV data to a file
            Path csvFilePath = Paths.get("CSV", "VehicleManagementData.csv");
            Files.createDirectories(csvFilePath.getParent());
            Files.writeString(csvFilePath, csvData);

            // Provide a link to download the saved CSV file
            String downloadLink = "/CSV/VehicleManagementData.csv";
            return ResponseEntity.ok().body("CSV file exported successfully. Download it from: " + downloadLink);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting CSV file");
        }
    }
}
