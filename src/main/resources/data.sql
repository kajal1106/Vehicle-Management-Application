-- Insert dummy data for Owner
INSERT INTO Owner (first_name, last_name) VALUES
('John', 'Doe'),
('Alice', 'Smith'),
('Bob', 'Johnson');


-- Inserting dummy data into the Vehicle table
INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('Toyota', 'ABC123', 'Petrol', 'Camry', 2022, 'Automatic', 25000, 1);

INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('Honda', 'XYZ456', 'Hybrid', 'Accord', 2021, 'CVT', 30000, 2);

INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('Ford', 'DEF789', 'Diesel', 'Mustang', 2023, 'Manual', 35000, 3);

INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('Chevrolet', 'GHI012', 'Gas', 'Cruze', 2022, 'Automatic', 28000, 1);

INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('Volkswagen', 'JKL345', 'Electric', 'ID.4', 2023, 'Automatic', 40000, 2);

INSERT INTO Vehicle (make, registration, engine, model, year, car_transmission, price, ownerid)
VALUES ('BMW', 'MNO678', 'Petrol', 'X5', 2021, 'Automatic', 55000, 3);

-- Add more vehicles as needed


-- Add more INSERT statements as needed


-- Insert dummy data for Maintenance with vehicle associations
-- Assuming maintenance_id 1 is associated with the first vehicle, maintenance_id 2 with the second, and so on.

-- Maintenance tasks associated with the first vehicle (Toyota)
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Oil Change', '2023-01-15', 1);
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Brake Inspection', '2023-02-28', 1);
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Tire Rotation', '2023-04-10', 1);

-- Maintenance tasks associated with the second vehicle (Honda)
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Oil Change', '2023-03-05', 2);
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Brake Inspection', '2023-05-20', 2);
-- Add more maintenance tasks as needed for the second vehicle

-- Maintenance tasks associated with the third vehicle (Ford)
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Oil Change', '2023-02-10', 3);
INSERT INTO Maintenance (task, maintenance_date, vehicleid) VALUES ('Brake Inspection', '2023-04-15', 3);
-- Add more maintenance tasks as needed for other vehicles


-- SELECT VEHICLE.PRICE FROM VEHICLE WHERE VEHICLE.MAKE = MAKE
-- SELECT VEHICLE.VEHICLE_ID, VEHICLE.CAR_TRANSMISSION, VEHICLE.ENGINE, VEHICLE.MAKE, VEHICLE.MODEL, VEHICLE.PRICE, VEHICLE.REGISTRATION, VEHICLE.YEAR, VEHICLE.OWNER_ID, OWNER.FIRST_NAME FROM VEHICLE JOIN OWNER ON VEHICLE.OWNER_ID = OWNER.OWNER_ID
