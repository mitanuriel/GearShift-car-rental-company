CREATE DATABASE bilabonnement;

USE bilabonnement;
Create user 'bruger5' identified  by '12345';
grant select, insert, delete, update on bilabonnement.* to 'bruger5';

CREATE TABLE car (
    car_id INT AUTO_INCREMENT,
    model VARCHAR(255),
    monthly_price FLOAT,
    brand VARCHAR(255),
    license_plate VARCHAR(255),
    chassis_number VARCHAR(255),
    co2_emissions INT,
    image MEDIUMBLOB,
    state ENUM('In storage', 'Being used', 'Under inspection'),
    PRIMARY KEY (car_id)
);

CREATE TABLE car_image (
    car_image_id INT AUTO_INCREMENT,
    image MEDIUMBLOB,
    car_id INT,
    PRIMARY KEY (car_image_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE equipment (
    equipment_id INT AUTO_INCREMENT,
    name VARCHAR(255),
    amount INT,
    car_id INT,
    PRIMARY KEY (equipment_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (customer_id)
);

CREATE TABLE contract (
    contract_id INT AUTO_INCREMENT,
    customer_id INT,
    car_id INT,
    contract_start DATE,
    contract_end DATE,
    price FLOAT,
    PRIMARY KEY (contract_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE damages (
    damages_id INT AUTO_INCREMENT,
    description TEXT,
    price FLOAT,
    contract_id INT,
    PRIMARY KEY (damages_id),
    FOREIGN KEY (contract_id) REFERENCES contract(contract_id)
);
