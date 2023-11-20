-- Insert dummy data for Owner
INSERT INTO Owner (first_name, last_name) VALUES
('John', 'Doe'),
('Alice', 'Smith'),
('Bob', 'Johnson');


-- Inserting dummy data into the Vehicle table
INSERT INTO Vehicle (make, registration_number, engine_type, model, manufacture_year, transmission_type, vehicle_price, owner_id)
VALUES ('Toyota', 'ABC123', 'Petrol', 'Camry', 2022, 'Automatic', 25000, 1);

INSERT INTO Vehicle (make, registration_number, engine_type, model, manufacture_year, transmission_type, vehicle_price, owner_id)
VALUES ('Honda', 'XYZ456', 'Hybrid', 'Accord', 2021, 'CVT', 30000, 2);

INSERT INTO Vehicle (make, registration_number, engine_type, model, manufacture_year, transmission_type, vehicle_price, owner_id)
VALUES ('Ford', 'DEF789', 'Diesel', 'Mustang', 2023, 'Manual', 35000, 3);

-- Add more INSERT statements as needed





-- Insert dummy data for Maintenance
INSERT INTO Maintenance (task) VALUES
('Oil Change'),
('Brake Inspection'),
('Tire Rotation');