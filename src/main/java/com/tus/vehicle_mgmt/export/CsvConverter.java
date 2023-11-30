package com.tus.vehicle_mgmt.export;

import java.util.List;

import com.tus.vehicle_mgmt.maintenance.MaintenanceDTO;
import com.tus.vehicle_mgmt.owner.OwnerDTO;
import com.tus.vehicle_mgmt.vehicle.VehicleDTO;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.StringJoiner;

public class CsvConverter {
    public static String convertDataToCsv(List<VehicleDTO> vehicles, List<OwnerDTO> owners, List<MaintenanceDTO> maintenanceList) {
        StringBuilder csvData = new StringBuilder();

        // Append CSV headers for each table
        csvData.append("Vehicle Make,Registration,Engine,Model,Year,Price\n");

        // Append each table's data
        for (VehicleDTO vehicle : vehicles) {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(vehicle.getMake());
            joiner.add(vehicle.getRegistration());
            joiner.add(vehicle.getEngine());
            joiner.add(vehicle.getModel());
            joiner.add(String.valueOf(vehicle.getYear()));
            joiner.add(String.valueOf(vehicle.getPrice()));
            csvData.append(joiner.toString()).append("\n");
        }
        csvData.append("\n\nOwner First Name,Last Name,Owner ID\n");

        for (OwnerDTO owner : owners) {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(owner.getFirstName());
            joiner.add(owner.getLastName());
            joiner.add(String.valueOf(owner.getId()));
            csvData.append(joiner.toString()).append("\n");
        }
        csvData.append("\n\nMaintenance Task,Due Date,Maintenance ID\n");

        for (MaintenanceDTO maintenance : maintenanceList) {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(maintenance.getTask());
            joiner.add(maintenance.getDueDate().toString()); // Convert LocalDate to String
            joiner.add(String.valueOf(maintenance.getId()));
            csvData.append(joiner.toString()).append("\n");
        }

        return csvData.toString();
    }

    public static void saveCsvFile(List<VehicleDTO> vehicles, List<OwnerDTO> owners, List<MaintenanceDTO> maintenanceList, String fileName) throws IOException {
        // Construct the Path object using the provided file name
        Path csvFilePath = Paths.get("CSV", fileName);
        
        // Ensure the parent directory exists
        Files.createDirectories(csvFilePath.getParent());

        // Write CSV data to the file
        Files.writeString(csvFilePath, convertDataToCsv(vehicles, owners, maintenanceList));
    }
}