package com.tus.vehicle_mgmt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.tus.vehicle_mgmt")
@EnableJpaRepositories("com.tus.vehicle_mgmt")
public class ApplicationConfig {}
