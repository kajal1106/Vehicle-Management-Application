package com.tus.vehicle_mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.tus.vehicle_mgmt.vehicle", "com.tus.vehicle_mgmt.owner", "com.tus.vehicle_mgmt.maintenance"})
public class VehicleMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleMgmtApplication.class, args);
		System.out.println("Hello there! Vehicle Management Application is running!");
	}

}